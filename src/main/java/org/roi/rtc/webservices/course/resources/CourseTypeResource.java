package org.roi.rtc.webservices.course.resources;

import org.roi.rtc.webservices.course.entities.CourseType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;

/**
 * WebService Resource
 * Provides with {@link CourseType}
 *
 * @author Vladislav Pikus
 */
@Path(value = "/course_type")
@Produces(MediaType.APPLICATION_JSON)
public class CourseTypeResource {

    /**
     * Return all types
     *
     * @return
     */
    @GET
    public Collection<CourseType> getAll() {
        return Arrays.asList(CourseType.values());
    }

}