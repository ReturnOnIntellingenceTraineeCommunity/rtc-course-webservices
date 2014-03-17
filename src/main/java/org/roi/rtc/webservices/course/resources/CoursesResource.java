package org.roi.rtc.webservices.course.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.IntParam;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.entities.Courses;
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

    private final CoursesDao dao;

    public CoursesResource(CoursesDao dao) {
        this.dao = dao;
    }

    /**
     * Find a course by id
     *
     * @param id course id
     * @return course object
     */
    @GET
    @UnitOfWork
    @Path("{id}")
    public Courses findById(@PathParam("id") IntParam id) {
        Courses courses = dao.findById(id.get());
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
        return dao.findAll();
    }

    /**
     * Save new course
     *
     * @param courses course for save
     * @return saved course
     */
    @POST
    @UnitOfWork
    public Courses create(Courses courses) {
        String code;
        do {
            code = UUID.randomUUID().toString();
        } while (dao.exist(code));
        courses.setCode(code);
        return dao.create(courses);
    }

    /**
     * Update existing course
     *
     * @param id      course id
     * @param courses course for update
     * @return updated course
     */
    @PUT
    @UnitOfWork
    @Path("{id}")
    public Courses update(@PathParam("id") IntParam id, Courses courses) {
        checkArgument(id.get().equals(courses.getId()));
        return dao.update(courses);
    }

    /**
     * Delete a course
     *
     * @param id course id
     */
    @DELETE
    @UnitOfWork
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id) {
        dao.delete(id.get());
    }

    /**
     * Delete all courses
     */
    @DELETE
    @UnitOfWork
    public void deleteAll() {
        dao.deleteAll();
    }

    /**
     * Find collection of courses by filter param
     * For example: courses/filter?name=test&date=11-01-2010&categories=dev;qa&tags=java;spring&pageNumber=1&maxResult=10
     * Query params such as category and tags is array and items to be separated ";" delim
     * Query params such as date by the following rule: dd-MM-yyyy
     *
     * @param name title for search
     * @param date start date
     * @param categories categories array
     * @param tags tags array
     * @param maxResult courses count per page (started on 0)
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
        Page page = new Page.Builder(dao.getCount()).page(pageNumber).maxResult(maxResult)
                .build();
        return dao.findByFilter(filter, page);
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
        return dao.getCount();
    }
}
