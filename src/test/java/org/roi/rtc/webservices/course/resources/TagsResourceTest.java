package org.roi.rtc.webservices.course.resources;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.yammer.dropwizard.testing.ResourceTest;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.roi.rtc.webservices.course.dao.TagsDao;
import org.roi.rtc.webservices.course.dao.impl.TagsDaoImpl;
import org.roi.rtc.webservices.course.entities.Tags;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Vladislav Pikus
 */
public class TagsResourceTest extends ResourceTest {
    private Tags tag;
    private TagsDao mockDao;

    @Override
    protected void setUpResources() throws Exception {
        mockDao = mock(TagsDaoImpl.class);
        addResource(new TagsResource(mockDao));
    }

    @Before
    public void setUp() throws Exception {
        tag = new Tags("Java");
        tag.setId(1);
    }

    @After
    public void tearDown() throws Exception {
        this.tearDownJersey();
    }

   @Test
    public void testFindById() throws Exception {
        when(mockDao.findById(1)).thenReturn(tag);
        String actual = client().resource("/tags/1").get(String.class);
        assertEquals(asJson(tag), actual);
    }

    @Test(expected = UniformInterfaceException.class)
    public void testFindByIdNull() {
        when(mockDao.findById(2)).thenReturn(null);
        Tags actual = client().resource("/tags/2").get(Tags.class);
        assertNull(actual);
    }

    @Test
    public void testFindAll() throws Exception {
        Collection<Tags> expected = Arrays.asList(tag);
        when(mockDao.findAll()).thenReturn(expected);
        String actual = client().resource("/tags").get(String.class);
        assertEquals(asJson(expected), actual);
    }

    @Test
    public void testCreate() throws Exception {
        tag.setId(null);
        when(mockDao.create(tag)).thenReturn(tag);
        when(mockDao.exist(any(Tags.class))).thenReturn(false);
        Tags actual = client().resource("/tags").type(MediaType.APPLICATION_JSON).post(Tags.class, tag);
        assertEquals(actual, tag);
        verify(mockDao).create(tag);
    }

    /* something wrong. It's magic!
    @Test
    public void testCreateExist() throws Exception {
        when(mockDao.exist(any(Tags.class))).thenReturn(true);
        client().resource("/tags").type(MediaType.APPLICATION_JSON).post(String.class, asJson(tag));
        verify(mockDao).exist(any(Tags.class));
    }*/

    @Test
    public void testUpdate() throws Exception {
        when(mockDao.findById(1)).thenReturn(tag);
        when(mockDao.update(tag)).thenReturn(tag);
        Tags actual = client().resource("/tags/1").type(MediaType.APPLICATION_JSON).put(Tags.class, tag);
        assertEquals(asJson(actual), asJson(tag));
        verify(mockDao).update(tag);
    }

    @Test
    public void testDelete() throws Exception {
        client().resource("/tags/1").delete();
        verify(mockDao).delete(1);
    }

    @Test
    public void testDeleteAll() throws Exception {
        client().resource("/tags").delete();
        verify(mockDao).deleteAll();
    }
}
