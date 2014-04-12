package net.github.rtc.micro.course.resource;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.yammer.dropwizard.testing.ResourceTest;
import net.github.rtc.micro.course.dao.CourseDao;
import net.github.rtc.micro.course.dao.impl.CourseDaoImpl;
import net.github.rtc.micro.course.dao.impl.TagDaoImpl;
import net.github.rtc.micro.course.model.dto.CourseSearchCriteriaDto;
import net.github.rtc.micro.course.model.dto.CourseSearchResult;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import net.github.rtc.micro.course.dao.AuthorDao;
import net.github.rtc.micro.course.dao.TagDao;
import net.github.rtc.micro.course.dao.impl.AuthorDaoImpl;
import net.github.rtc.micro.course.entities.*;
import net.github.rtc.micro.course.model.dto.PageDto;

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
public class CourseResourceTest extends ResourceTest {

    private Course course;
    private CourseDao mockCourseDao;
    private AuthorDao mockAuthorDao;
    private TagDao mockTagDao;
    private Date start;
    private Date end;
    private Date publish;
    private String code = "sdjasd982-sdasd-2323";
    private Author author;

    @Override
    protected void setUpResources() throws Exception {
        mockCourseDao = mock(CourseDaoImpl.class);
        mockAuthorDao = mock(AuthorDaoImpl.class);
        mockTagDao = mock(TagDaoImpl.class);
        addResource(new CourseResource(mockCourseDao, mockAuthorDao, mockTagDao));
    }

    @Before
    public void setUp() throws Exception {
        start = DateTime.now().toDate();
        end = DateTime.now().toDate();
        author = new Author("Vasya", "Pupkin", "vasia@gmail.com");
        course = new Course("codeTest", "nameTest", CourseType.DEV, author, start,
                end, publish, 10, "super description", Status.DRAFT);
        course.setId(1);
    }

    @After
    public void tearDown() throws Exception {
        this.tearDownJersey();
    }

    @Test
    public void testFindById() throws Exception {
        when(mockCourseDao.findByCode(code)).thenReturn(course);
        String actual = client().resource("/courses/" + code).get(String.class);
        assertEquals(asJson(course), actual);
    }

    @Test(expected = UniformInterfaceException.class)
    public void testFindByIdNull() {
        when(mockCourseDao.findById(2)).thenReturn(null);
        Course actual = client().resource("/courses/2").get(Course.class);
        assertNull(actual);
    }

    @Test
    public void testCreate() throws Exception {
        when(mockCourseDao.merge(any(Course.class))).thenReturn(course);
        when(mockAuthorDao.findByEmail("vasia@gmail.com")).thenReturn(author);
        when(mockCourseDao.exist(anyString())).thenReturn(false);
        Course actual = client().resource("/courses").type(MediaType.APPLICATION_JSON).post(Course.class, course);
        course.setCode(actual.getCode());
        assertEquals(asJson(actual), asJson(course));
        verify(mockCourseDao).merge(any(Course.class));
    }

    @Test
    public void testUpdate() throws Exception {
        course.setCode(code);
        when(mockCourseDao.findByCode(code)).thenReturn(course);
        when(mockCourseDao.merge(course)).thenReturn(course);
        when(mockAuthorDao.findByEmail("vasia@gmail.com")).thenReturn(author);
        List<Tag> tags = Arrays.asList(new Tag("Java"), new Tag("Hibernate"));
        for (Tag tag : tags) {
            when(mockTagDao.findByValue(tag.getValue())).thenReturn(tag);
        }
        course.setTags(tags);
        Course actual = client().resource("/courses/" + code).type(MediaType.APPLICATION_JSON).put(Course.class, course);
        assertEquals(asJson(actual), asJson(course));
        verify(mockCourseDao).merge(course);
    }

    @Test
    public void testDelete() throws Exception {
        client().resource("/courses/" + code).delete(code);
        verify(mockCourseDao).delete(code);
    }

    @Test
    public void testDeleteAll() throws Exception {
        client().resource("/courses").delete();
        verify(mockCourseDao).deleteAll();
    }

    @Test
    @Ignore
    public void testFiltering() throws Exception {
        Collection<Course> courses = Arrays.asList(course);
        when(mockCourseDao.findByCriteria(any(CourseSearchCriteriaDto.class), any(PageDto.class))).thenReturn(courses);
        when(mockCourseDao.getCount(any(CourseSearchCriteriaDto.class))).thenReturn(1);
        String result = client().resource("/courses")
                .queryParam("name", "1")
                .queryParam("date", "11-01-1993")
                .queryParam("categories", "1;2")
                .queryParam("tags", "1")
                .type(MediaType.APPLICATION_JSON).get(String.class);
        CourseSearchResult dto = new CourseSearchResult.Builder().courses(courses)
                .totalCount(1)
                .limit(0)
                .offset(0).build();
        assertEquals(result, asJson(dto));
    }
}
