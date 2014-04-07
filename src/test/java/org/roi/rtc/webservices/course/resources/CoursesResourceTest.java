package org.roi.rtc.webservices.course.resources;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.yammer.dropwizard.testing.ResourceTest;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.dao.TagsDao;
import org.roi.rtc.webservices.course.dao.impl.AuthorDaoImpl;
import org.roi.rtc.webservices.course.dao.impl.CoursesDaoImpl;
import org.roi.rtc.webservices.course.dao.impl.TagsDaoImpl;
import org.roi.rtc.webservices.course.entities.*;
import org.roi.rtc.webservices.course.model.CourseFilter;
import org.roi.rtc.webservices.course.model.CoursesDTO;
import org.roi.rtc.webservices.course.model.Page;

import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Vladislav Pikus
 */
public class CoursesResourceTest extends ResourceTest {

    private Courses course;
    private CoursesDao mockCoursesDao;
    private AuthorDao mockAuthorDao;
    private TagsDao mockTagsDao;
    private Date start;
    private Date end;
    private Date publish;
    private String code = "sdjasd982-sdasd-2323";
    private Author author;

    @Override
    protected void setUpResources() throws Exception {
        mockCoursesDao = mock(CoursesDaoImpl.class);
        mockAuthorDao = mock(AuthorDaoImpl.class);
        mockTagsDao = mock(TagsDaoImpl.class);
        addResource(new CoursesResource(mockCoursesDao, mockAuthorDao, mockTagsDao));
    }

    @Before
    public void setUp() throws Exception {
        start = DateTime.now().toDate();
        end = DateTime.now().toDate();
        author = new Author("Vasya", "Pupkin", "vasia@gmail.com");
        course = new Courses("codeTest", "nameTest", CourseType.DEV, author, start,
                end, publish, 10, "super description", Status.DRAFT);
        course.setId(1);
    }

    @After
    public void tearDown() throws Exception {
        this.tearDownJersey();
    }

    @Test
    public void testFindById() throws Exception {
        when(mockCoursesDao.findByCode(code)).thenReturn(course);
        String actual = client().resource("/courses/" + code).get(String.class);
        assertEquals(asJson(course), actual);
    }

    @Test(expected = UniformInterfaceException.class)
    public void testFindByIdNull() {
        when(mockCoursesDao.findById(2)).thenReturn(null);
        Courses actual = client().resource("/courses/2").get(Courses.class);
        assertNull(actual);
    }

    @Test
    public void testCreate() throws Exception {
        when(mockCoursesDao.merge(any(Courses.class))).thenReturn(course);
        when(mockAuthorDao.findByEmail("vasia@gmail.com")).thenReturn(author);
        when(mockCoursesDao.exist(anyString())).thenReturn(false);
        Courses actual = client().resource("/courses").type(MediaType.APPLICATION_JSON).post(Courses.class, course);
        course.setCode(actual.getCode());
        assertEquals(asJson(actual), asJson(course));
        verify(mockCoursesDao).merge(any(Courses.class));
    }

    @Test
    public void testUpdate() throws Exception {
        course.setCode(code);
        when(mockCoursesDao.findByCode(code)).thenReturn(course);
        when(mockCoursesDao.merge(course)).thenReturn(course);
        when(mockAuthorDao.findByEmail("vasia@gmail.com")).thenReturn(author);
        List<Tags> tags = Arrays.asList(new Tags("Java"), new Tags("Hibernate"));
        for (Tags tag : tags) {
            when(mockTagsDao.findByValue(tag.getValue())).thenReturn(tag);
        }
        course.setTags(tags);
        Courses actual = client().resource("/courses/" + code).type(MediaType.APPLICATION_JSON).put(Courses.class, course);
        assertEquals(asJson(actual), asJson(course));
        verify(mockCoursesDao).merge(course);
    }

    @Test
    public void testDelete() throws Exception {
        client().resource("/courses/" + code).delete(code);
        verify(mockCoursesDao).delete(code);
    }

    @Test
    public void testDeleteAll() throws Exception {
        client().resource("/courses").delete();
        verify(mockCoursesDao).deleteAll();
    }

    @Test
    @Ignore
    public void testFiltering() throws Exception {
        Collection<Courses> courses = Arrays.asList(course);
        when(mockCoursesDao.findByFilter(any(CourseFilter.class), any(Page.class))).thenReturn(courses);
        when(mockCoursesDao.getCount(any(CourseFilter.class))).thenReturn(1);
        String result = client().resource("/courses")
                .queryParam("name", "1")
                .queryParam("date", "11-01-1993")
                .queryParam("categories", "1;2")
                .queryParam("tags", "1")
                .type(MediaType.APPLICATION_JSON).get(String.class);
        CoursesDTO dto = new CoursesDTO.Builder().courses(courses)
                .totalCount(1)
                .limit(0)
                .offset(0).build();
        assertEquals(result, asJson(dto));
    }
}
