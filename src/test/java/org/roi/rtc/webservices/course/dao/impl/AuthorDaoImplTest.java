package org.roi.rtc.webservices.course.dao.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.roi.rtc.webservices.course.entities.Author;
import org.roi.rtc.webservices.course.entities.CourseType;
import org.roi.rtc.webservices.course.entities.Courses;
import org.roi.rtc.webservices.course.entities.Tags;
import org.skife.jdbi.v2.DBI;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

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
        WebResource service = Client.create().resource("http://localhost:8079/api/");
        /*System.out.println(service.path("courses/filter")
                .queryParam("name", "1")
                .queryParam("date", "11-01-1993")
                .queryParam("categories", "1")
                .queryParam("tags", "1")
                .type(MediaType.APPLICATION_JSON).get(String.class));*/
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
