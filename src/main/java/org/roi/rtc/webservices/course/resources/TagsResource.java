package org.roi.rtc.webservices.course.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.IntParam;
import org.roi.rtc.webservices.course.dao.TagsDao;
import org.roi.rtc.webservices.course.entities.Tags;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Vladislav Pikus
 */
@Path(value = "/tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagsResource {
    private final TagsDao dao;

    public TagsResource(TagsDao dao) {
        this.dao = dao;
    }

    @GET
    @UnitOfWork
    @Path("{id}")
    public Tags findById(@PathParam("id") IntParam id) {
        Tags tags = dao.findById(id.get());
        if (tags == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return tags;
    }

    @GET
    @UnitOfWork
    public Collection<Tags> findAll() {
        return dao.findAll();
    }

    @POST
    @UnitOfWork
    public Tags create(Tags tag) {
        if (dao.exist(tag)) {
            return null;
        }
        return dao.create(tag);
    }

    @PUT
    @UnitOfWork
    @Path("{id}")
    public Tags update(@PathParam("id") IntParam id, Tags tag) {
        checkArgument(id.get().equals(tag.getId()));
        return dao.update(tag);
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
