package org.roi.rtc.webservices.course.dao.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.roi.rtc.webservices.course.entities.Author;
import org.roi.rtc.webservices.course.entities.CourseType;
import org.roi.rtc.webservices.course.entities.Courses;
import org.roi.rtc.webservices.course.entities.Tags;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 19.02.14
 * Time: 18:34
 * To change this template use File | Settings | File Templates.
 */
public class AuthorDaoImplTest {
    private AuthorDaoImpl dao;

    @Before
    public void setUp() throws Exception {
        DBI dbi = new DBI("jdbc:h2:mem:test");
        //dao = dbi.open(AuthorDaoImpl.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        //HttpServer server = HttpServerFactory.create("http://localhost:8080/");
        //server.start();
        WebResource service = Client.create().resource("http://146.185.176.193:8079/method/");
        Courses courses = new Courses("codeTest", "Testing testing111", CourseType.DEV, new Author("Vasya", "Pupkin", "vasia@gmail.com"), DateTime.now().toDate(), DateTime.now().toDate());
        //List<Tags> tagses = Arrays.asList(new Tags("Java"), new Tags("Hibernate"));
        //courses.setTags(tagses);
        //service.path("courses").type(MediaType.APPLICATION_JSON).post(String.class, asJson(courses));
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testGetCount() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {

    }
}
