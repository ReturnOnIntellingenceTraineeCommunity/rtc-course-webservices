package org.roi.rtc.webservices.course.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.IntParam;
import org.roi.rtc.webservices.course.dao.TagsDao;
import org.roi.rtc.webservices.course.entities.Tags;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * WebService Resource
 * Provides with {@link Tags}
 *
 * @author Vladislav Pikus
 */
@Path(value = "/tags")
@Produces(MediaType.APPLICATION_JSON)
public class TagsResource {
    private static Logger LOG = LoggerFactory.getLogger(AuthorResource.class.getName());

    private final TagsDao dao;

    public TagsResource(TagsDao dao) {
        this.dao = dao;
    }

    /**
     * Find a tag by id
     *
     * @param id tag id
     * @return tag
     */
    @GET
    @UnitOfWork
    @Path("{id}")
    public Tags findById(@PathParam("id") IntParam id) {
        Tags tags = dao.findById(id.get());
        if (tags == null) {
            RuntimeException ex = new WebApplicationException(Response.Status.NOT_FOUND);
            LOG.error("Exception: ", ex);
            throw ex;
        }
        return tags;
    }

    /**
     * Find collection of tags
     *
     * @return collection tags
     */
    @GET
    @UnitOfWork
    public Collection<Tags> findAll() {
        return dao.findAll();
    }

    /**
     * Save new tag
     *
     * @param tag tag for save
     * @return saved tag
     */
    @POST
    @UnitOfWork
    public Tags create(Tags tag) {
        if (dao.exist(tag)) {
            return null;
        }
        return dao.create(tag);
    }

    /**
     * Update existing tag
     *
     * @param id tag id
     * @param tag tag for update
     * @return updated tag
     */
    @PUT
    @UnitOfWork
    @Path("{id}")
    public Tags update(@PathParam("id") IntParam id, Tags tag) {
        Tags oldTag = dao.findById(id.get());
        if (oldTag != null) {
            tag.setId(oldTag.getId());
        }
        checkArgument(id.get().equals(tag.getId()));
        return dao.update(tag);
    }

    /**
     * Delete a tag by id
     *
     * @param id tag id
     */
    @DELETE
    @UnitOfWork
    @Path("{id}")
    public void delete(@PathParam("id") IntParam id) {
        dao.delete(id.get());
    }

    /**
     * Delete all tags
     */
    @DELETE
    @UnitOfWork
    public void deleteAll() {
        dao.deleteAll();
    }
}
