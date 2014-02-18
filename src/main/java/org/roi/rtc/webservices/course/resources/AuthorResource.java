package org.roi.rtc.webservices.course.resources;

import org.roi.rtc.webservices.course.entities.Author;
import org.roi.rtc.webservices.course.service.AuthorService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 18.02.14
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
@Path("/author.get")
public class AuthorResource {
    private AuthorService service;

    public AuthorResource(AuthorService service) {
        this.service = service;
    }

    @GET
    public Author findPerson(@PathParam("id") Integer id) {
        return service.findById(id);
    }
}
