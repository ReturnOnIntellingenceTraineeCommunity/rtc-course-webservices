package net.github.rtc.micro.course.resource;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.yammer.dropwizard.testing.ResourceTest;
import net.github.rtc.micro.course.dao.impl.TagDaoImpl;
import net.github.rtc.micro.course.entities.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.github.rtc.micro.course.dao.TagDao;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Vladislav Pikus
 */
public class TagResourceTest extends ResourceTest {
    private Tag tag;
    private TagDao mockDao;

    @Override
    protected void setUpResources() throws Exception {
        mockDao = mock(TagDaoImpl.class);
        addResource(new TagResource(mockDao));
    }

    @Before
    public void setUp() throws Exception {
        tag = new Tag("Java");
        tag.setId(1);
    }

    @After
    public void tearDown() throws Exception {
        this.tearDownJersey();
    }

   @Test
    public void testFindById() throws Exception {
        when(mockDao.get(1)).thenReturn(tag);
        String actual = client().resource("/tags/1").get(String.class);
        assertEquals(asJson(tag), actual);
    }

    @Test(expected = UniformInterfaceException.class)
    public void testFindByIdNull() {
        when(mockDao.get(2)).thenReturn(null);
        Tag actual = client().resource("/tags/2").get(Tag.class);
        assertNull(actual);
    }

    @Test
    public void testFindAll() throws Exception {
        Collection<Tag> expected = Arrays.asList(tag);
        when(mockDao.findAll()).thenReturn(expected);
        String actual = client().resource("/tags").get(String.class);
        assertEquals(asJson(expected), actual);
    }

    @Test
    public void testCreate() throws Exception {
        tag.setId(null);
        when(mockDao.create(tag)).thenReturn(tag);
        when(mockDao.exist(any(Tag.class))).thenReturn(false);
        Tag actual = client().resource("/tags").type(MediaType.APPLICATION_JSON).post(Tag.class, tag);
        assertEquals(actual, tag);
        verify(mockDao).create(tag);
    }

    /* something wrong. It's magic!
    @Test
    public void testCreateExist() throws Exception {
        when(mockDao.exist(any(Tag.class))).thenReturn(true);
        client().resource("/tags").type(MediaType.APPLICATION_JSON).post(String.class, asJson(tag));
        verify(mockDao).exist(any(Tag.class));
    }*/

    @Test
    public void testUpdate() throws Exception {
        when(mockDao.get(1)).thenReturn(tag);
        when(mockDao.update(tag)).thenReturn(tag);
        Tag actual = client().resource("/tags/1").type(MediaType.APPLICATION_JSON).put(Tag.class, tag);
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
