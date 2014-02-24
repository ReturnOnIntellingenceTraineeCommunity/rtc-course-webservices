package org.roi.rtc.webservices.course.dao.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.skife.jdbi.v2.DBI;

import java.util.UUID;

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
