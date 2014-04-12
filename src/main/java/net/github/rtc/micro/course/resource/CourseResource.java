package net.github.rtc.micro.course.resource;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import net.github.rtc.micro.course.dao.AuthorDao;
import net.github.rtc.micro.course.dao.CourseDao;
import net.github.rtc.micro.course.dao.TagDao;
import net.github.rtc.micro.course.entities.Author;
import net.github.rtc.micro.course.entities.Course;
import net.github.rtc.micro.course.entities.Tag;
import net.github.rtc.micro.course.model.dto.CourseSearchCriteriaDto;
import net.github.rtc.micro.course.model.dto.CourseSearchResult;
import net.github.rtc.micro.course.model.dto.PageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * WebService Resource
 * Provides with {@link net.github.rtc.micro.course.entities.Course}
 *
 * @author Vladislav Pikus
 */
@Path(value = "/courses")
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

    private static Logger LOG = LoggerFactory.getLogger(AuthorResource.class.getName());

    private final CourseDao courseDao;
    private final AuthorDao authorDao;
    private final TagDao tagDao;


    public CourseResource(CourseDao courseDao, AuthorDao authorDao, TagDao tagDao) {
        this.courseDao = courseDao;
        this.authorDao = authorDao;
        this.tagDao = tagDao;
    }

    /**
     * Find a course by code
     *
     * @param code course code
     * @return course object
     */
    @GET
    @UnitOfWork
    @Path("{code}")
    public Course findByCode(@PathParam("code") String code) {
        Course course = courseDao.findByCode(code);
        if (course == null) {
            RuntimeException ex = new WebApplicationException(Response.Status.NOT_FOUND);
            LOG.error("Exception: ", ex);
            throw ex;
        }
        return course;
    }

    /**
     * Update keys in assosiated entity
     *
     * @param course course object
     */
    private void updateKeys(final Course course) {
        final Collection<Tag> tags = course.getTags();
        if (tags != null) {
            for (final Tag tag : tags) {
                final Tag oldTag = tagDao.findByValue(tag.getValue());
                if (oldTag != null) {
                    tag.setId(oldTag.getId());
                }
            }
        }
        final Author author = course.getAuthor();
        final Author oldAuthor = authorDao.findByEmail(author.getEmail());
        if (oldAuthor != null && author.getFirstName().equals(oldAuthor.getFirstName())
                && author.getLastName().equals(oldAuthor.getLastName())) {
            author.setId(oldAuthor.getId());
        }
    }

    /**
     * Save new course
     *
     * @param course course for save
     * @return saved course
     */
    @POST
    @UnitOfWork
    public Course create(Course course) {
        String code;
        do {
            code = UUID.randomUUID().toString();
        } while (courseDao.exist(code));
        course.setCode(code);
        updateKeys(course);
        return courseDao.merge(course);
    }

    /**
     * Update existing course
     *
     * @param code   course code
     * @param course course for update
     * @return updated course
     */
    @PUT
    @UnitOfWork
    @Path("{code}")
    public Course update(@PathParam("code") String code, Course course) {
        checkArgument(code.equals(course.getCode()));
        Course oldCourse = courseDao.findByCode(code);
        if (oldCourse == null) {
            RuntimeException ex = new WebApplicationException(Response.Status.NOT_FOUND);
            LOG.error("Exception: ", ex);
            throw ex;
        }
        course.setId(oldCourse.getId());
        updateKeys(course);
        return courseDao.merge(course);
    }

    /**
     * Delete a course
     *
     * @param code course code
     */
    @DELETE
    @UnitOfWork
    @Path("{code}")
    public void delete(@PathParam("code") String code) {
        courseDao.delete(code);
    }

    /**
     * Delete all courses
     */
    @DELETE
    @UnitOfWork
    public void deleteAll() {
        courseDao.deleteAll();
    }

    /**
     * Find collection of courses by filter param
     * For example: courses/filter?name=test&date=11-01-2010&categories=dev;qa&tags=java;spring&pageNumber=1&maxResult=10
     * Query params such as category and tags is array and items to be separated ";" delim
     * Query params such as date by the following rule: dd-MM-yyyy
     *
     * @param name       title for search
     * @param date       start date
     * @param categories categories array
     * @param tags       tags array
     * @param maxResult  courses count per page (started on 0)
     * @param pageNumber current page (started on 0)
     * @return coursesDTO
     */
    @GET
    @UnitOfWork
    public CourseSearchResult search(@QueryParam("name") String name,
                                     @QueryParam("date") String date,
                                     @QueryParam("categories") String categories,
                                     @QueryParam("tags") String tags,
                                     @QueryParam("status") String status,
                                     @QueryParam("pageNumber") int pageNumber,
                                     @QueryParam("maxResult") int maxResult) {
        CourseSearchCriteriaDto filter = new CourseSearchCriteriaDto.Builder().title(name).startDate(date)
                .categories(categories).tags(tags).status(status).build();
        Integer total = courseDao.getCount(filter);
        PageDto pageDto = new PageDto.Builder(total).page(pageNumber).maxResult(maxResult)
                .build();
        CourseSearchResult dto = new CourseSearchResult.Builder().courses(courseDao.findByCriteria(filter, pageDto))
                .totalCount(total)
                .limit(pageDto.getMaxResult())
                .offset(pageDto.getFirstResult()).build();
        return dto;
    }
}
