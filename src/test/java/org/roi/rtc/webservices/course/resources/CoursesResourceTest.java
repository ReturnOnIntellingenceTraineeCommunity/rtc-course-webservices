package org.roi.rtc.webservices.course.resources;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.yammer.dropwizard.testing.ResourceTest;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.dao.impl.CoursesDaoImpl;
import org.roi.rtc.webservices.course.entities.Author;
import org.roi.rtc.webservices.course.entities.CourseType;
import org.roi.rtc.webservices.course.entities.Courses;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Vladislav Pikus
 */
public class CoursesResourceTest extends ResourceTest {

    private Courses course;
    private CoursesDao mockDao;
    private Date start;
    private Date end;

    @Override
    protected void setUpResources() throws Exception {
        mockDao = mock(CoursesDaoImpl.class);
        addResource(new CoursesResource(mockDao));
    }

    @Before
    public void setUp() throws Exception {
        start = DateTime.now().toDate();
        end = DateTime.now().toDate();
        course = new Courses("codeTest", "nameTest", CourseType.DEV, new Author("Vasya", "Pupkin", "vasia@gmail.com"), start, end);
        course.setId(1);
    }

    @After
    public void tearDown() throws Exception {
        this.tearDownJersey();
    }

    @Test
    public void testFindById() throws Exception {
        when(mockDao.findById(1)).thenReturn(course);
        Courses actual = client().resource("/courses/1").get(Courses.class);
        assertEquals(course, actual);
    }

    @Test(expected = UniformInterfaceException.class)
    public void testFindByIdNull() {
        when(mockDao.findById(2)).thenReturn(null);
        Courses actual = client().resource("/courses/2").get(Courses.class);
        assertNull(actual);
    }

    @Test
    public void testFindAll() throws Exception {
        Collection<Courses> expected = Arrays.asList(course);
        when(mockDao.findAll()).thenReturn(expected);
        String actual = client().resource("/courses").get(String.class);
        assertEquals(asJson(expected), actual);
    }

    @Test
    public void testCreate() throws Exception {
        when(mockDao.create(course)).thenReturn(course);
        when(mockDao.exist(anyString())).thenReturn(false);
        /*Courses actual = client().resource("/courses").type(MediaType.APPLICATION_JSON).post(Courses.class, course);
        course.setCode(actual.getCode());
        assertEquals(actual, course);
        verify(mockDao).create(course);*/
    }

    @Test
    public void testUpdate() throws Exception {
        when(mockDao.update(course)).thenReturn(course);
        Courses actual = client().resource("/courses/1").type(MediaType.APPLICATION_JSON).put(Courses.class, course);
        assertEquals(actual, course);
        verify(mockDao).update(course);
    }

    @Test
    public void testDelete() throws Exception {
        client().resource("/courses/1").delete();
        verify(mockDao).delete(1);
    }

    @Test
    public void testDeleteAll() throws Exception {
        client().resource("/courses").delete();
        verify(mockDao).deleteAll();
    }
}
