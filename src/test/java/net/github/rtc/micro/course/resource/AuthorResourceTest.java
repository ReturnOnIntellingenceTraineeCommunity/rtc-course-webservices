package net.github.rtc.micro.course.resource;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.yammer.dropwizard.testing.ResourceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.github.rtc.micro.course.dao.AuthorDao;
import net.github.rtc.micro.course.dao.impl.AuthorDaoImpl;
import net.github.rtc.micro.course.entities.Author;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * @author Vladislav Pikus
 */
public class AuthorResourceTest extends ResourceTest {
    private Author author;
    private AuthorDao mockDao;

    @Before
    public void setUp() throws Exception {
        author = new Author("Ivan", "IvanOff", "ivanoff@gmail.com");
        author.setId(1);
    }

    @After
    public void tearDown() throws Exception {
        this.tearDownJersey();
    }

    @Test
    public void testFindById() throws Exception {
        when(mockDao.get(1)).thenReturn(author);
        String actual = client().resource("/author/1").get(String.class);

        assertEquals(asJson(author), actual);

    }

    @Test(expected = UniformInterfaceException.class)
    public void testFindByIdNull() {
        when(mockDao.get(2)).thenReturn(null);
        Author actual = client().resource("/author/2").get(Author.class);
        assertNull(actual);
    }

    @Test
    public void testFindAll() throws Exception {
        Collection<Author> authorCollection = Arrays.asList(author);
        when(mockDao.findAll()).thenReturn(authorCollection);
        String actual = client().resource("/author").get(String.class);
        assertEquals(asJson(authorCollection), actual);
    }

    @Test
    public void testCreate() throws Exception {
        author.setId(null);
        when(mockDao.create(author)).thenReturn(author);
        Author actual = client().resource("/author").type(MediaType.APPLICATION_JSON).post(Author.class, author);
        assertEquals(actual, author);
        verify(mockDao).create(author);
    }

    @Test
    public void testUpdate() throws Exception {
        when(mockDao.update(author)).thenReturn(author);
        when(mockDao.get(1)).thenReturn(author);
        Author actual = client().resource("/author/1").type(MediaType.APPLICATION_JSON).put(Author.class, author);
        assertEquals(asJson(actual), asJson(author));
        verify(mockDao).update(author);
    }

    @Test
    public void testDelete() throws Exception {
        client().resource("/author/1").delete();
        verify(mockDao).delete(1);
    }

    @Test
    public void testDeleteAll() throws Exception {
        client().resource("/author").delete();
        verify(mockDao).deleteAll();
    }

    @Override
    protected void setUpResources() throws Exception {
        mockDao = mock(AuthorDaoImpl.class);
        addResource(new AuthorResource(mockDao));
    }
}
