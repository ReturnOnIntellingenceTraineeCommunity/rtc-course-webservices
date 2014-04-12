package net.github.rtc.micro.course.dao.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

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
