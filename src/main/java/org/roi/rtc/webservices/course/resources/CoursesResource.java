package org.roi.rtc.webservices.course.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.dao.TagsDao;
import org.roi.rtc.webservices.course.entities.Author;
import org.roi.rtc.webservices.course.entities.Courses;
import org.roi.rtc.webservices.course.entities.Tags;
import org.roi.rtc.webservices.course.model.CourseFilter;
import org.roi.rtc.webservices.course.model.Page;
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
 * Provides with {@link Courses}
 *
 * @author Vladislav Pikus
 */
@Path(value = "/courses")
@Produces(MediaType.APPLICATION_JSON)
public class CoursesResource {

    private static Logger LOG = LoggerFactory.getLogger(AuthorResource.class.getName());

    private final CoursesDao coursesDao;
    private final AuthorDao authorDao;
    private final TagsDao tagsDao;


    public CoursesResource(CoursesDao coursesDao, AuthorDao authorDao, TagsDao tagsDao) {
        this.coursesDao = coursesDao;
        this.authorDao = authorDao;
        this.tagsDao = tagsDao;
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
    public Courses findByCode(@PathParam("code") String code) {
        Courses courses = coursesDao.findByCode(code);
        if (courses == null) {
            RuntimeException ex = new WebApplicationException(Response.Status.NOT_FOUND);
            LOG.error("Exception: ", ex);
            throw ex;
        }
        return courses;
    }

    /**
     * Find collection of courses
     *
     * @return collection of courses
     */
    @GET
    @UnitOfWork
    public Collection<Courses> findAll() {
        return coursesDao.findAll();
    }

    /**
     * Update keys in assosiated entity
     *
     * @param course course object
     */
    private void updateKeys(final Courses course) {
        final Collection<Tags> tags = course.getTags();
        if (tags != null) {
            for (final Tags tag : tags) {
                final Tags oldTag = tagsDao.findByValue(tag.getValue());
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
    public Courses create(Courses course) {
        String code;
        do {
            code = UUID.randomUUID().toString();
        } while (coursesDao.exist(code));
        course.setCode(code);
        updateKeys(course);
        return coursesDao.merge(course);
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
    public Courses update(@PathParam("code") String code, Courses course) {
        checkArgument(code.equals(course.getCode()));
        Courses oldCourse = coursesDao.findByCode(code);
        if (oldCourse == null) {
            RuntimeException ex = new WebApplicationException(Response.Status.NOT_FOUND);
            LOG.error("Exception: ", ex);
            throw ex;
        }
        course.setId(oldCourse.getId());
        updateKeys(course);
        return coursesDao.merge(course);
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
        coursesDao.delete(code);
    }

    /**
     * Delete all courses
     */
    @DELETE
    @UnitOfWork
    public void deleteAll() {
        coursesDao.deleteAll();
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
     * @return courses collection
     */
    @GET
    @Path("filter")
    @UnitOfWork
    public Collection<Courses> filtering(@QueryParam("name") String name,
                                         @QueryParam("date") String date,
                                         @QueryParam("categories") String categories,
                                         @QueryParam("tags") String tags,
                                         @QueryParam("pageNumber") int pageNumber,
                                         @QueryParam("maxResult") int maxResult) {
        CourseFilter filter = new CourseFilter.Builder().title(name).startDate(date)
                .categories(categories).tags(tags).build();
        Page page = new Page.Builder(coursesDao.getCount()).page(pageNumber).maxResult(maxResult)
                .build();
        return coursesDao.findByFilter(filter, page);
    }

    /**
     * Get courses count
     *
     * @return courses count
     */
    @GET
    @Path("count")
    @UnitOfWork
    public Integer getCount() {
        return coursesDao.getCount();
    }
}
