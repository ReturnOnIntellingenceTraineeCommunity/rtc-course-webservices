package org.roi.rtc.webservices.course.resources;

import org.roi.rtc.webservices.course.config.MessagesConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 14.02.14
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */

@Path(value = "/hello")
public class HelloResource {

    private final MessagesConfiguration conf;

    public HelloResource(MessagesConfiguration conf) {
        this.conf = conf;
    }

    @GET
    public String sayHello() {
        return conf.getHello();
    }
}


