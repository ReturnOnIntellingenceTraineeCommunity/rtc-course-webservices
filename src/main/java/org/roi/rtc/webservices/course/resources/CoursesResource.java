package org.roi.rtc.webservices.course.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.IntParam;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.entities.Courses;
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
}
