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

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Author resource
 *
 * @author Vladislav Pikus
 */
@Path(value = "/author")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private final AuthorDao dao;

    public AuthorResource(AuthorDao dao) {
        this.dao = dao;
    }

    /**
     * Find a author by id
     *
     * @param id identifier
     * @return find obj
     */
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

    /**
     * Find all author in the DB
     *
     * @return
     */
    @GET
    @Timed
    @UnitOfWork
    public Collection<Author> findAll() {
        return dao.findAll();
    }

    /**
     * Save a new author in the DB
     *
     * @param author author for save
     * @return saved author with id
     */
    @POST
    @UnitOfWork
    public Author create(Author author) {
        return dao.create(author);
    }

    /**
     * Update an existing author
     *
     * @param author
     * @param id
     * @return
     */
    @PUT
    @UnitOfWork
    @Path("{id}")
    public Author update(@PathParam("id") IntParam id, Author author) {
        checkArgument(id.get().equals(author.getId()));
        return dao.update(author);
    }

    /**
     * Delete a author by id
     *
     * @param id
     */
    @DELETE
    @UnitOfWork
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id) {
        dao.delete(id.get());
    }

    /**
     * Delete all rows
     */
    @DELETE
    @UnitOfWork
    public void deleteAll() {
        dao.deleteAll();
    }
}
