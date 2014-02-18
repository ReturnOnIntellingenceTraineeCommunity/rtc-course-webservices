package org.roi.rtc.webservices.course.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.metrics.annotation.Timed;
import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.roi.rtc.webservices.course.entities.Author;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 18.02.14
 * Time: 20:16
 * To change this template use File | Settings | File Templates.
 */
@Path(value = "/author")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorDao dao;

    public AuthorResource(AuthorDao dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("{id}")
    public Author findById(@PathParam("id") IntParam id) {
        Author author = dao.findById(id.get());
        if (author == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return author;
    }

    @GET
    @Timed
    @UnitOfWork
    public Collection<Author> findAll() {
        return dao.findAll();
    }
}
