package org.roi.rtc.webservices.course.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.IntParam;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.entities.Courses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Vladislav Pikus
 */
@Path(value = "/courses")
@Produces(MediaType.APPLICATION_JSON)
public class CoursesResource {

    private final CoursesDao dao;

    public CoursesResource(CoursesDao dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    @Path("{id}")
    public Courses findById(@PathParam("id") IntParam id) {
        Courses courses = dao.findById(id.get());
        if (courses == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return courses;
    }

    @GET
    @UnitOfWork
    public Collection<Courses> findAll() {
        return dao.findAll();
    }

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

    @PUT
    @UnitOfWork
    @Path("{id}")
    public Courses update(@PathParam("id") IntParam id, Courses courses) {
        checkArgument(id.get().equals(courses.getId()));
        return dao.update(courses);
    }

    @DELETE
    @UnitOfWork
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id) {
        dao.delete(id.get());
    }

    @DELETE
    @UnitOfWork
    public void deleteAll() {
        dao.deleteAll();
    }
}
