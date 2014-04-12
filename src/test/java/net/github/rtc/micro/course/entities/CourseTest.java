package net.github.rtc.micro.course.entities;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Eugene Lapshin
 */
public class CourseTest {

    private Course course;

    private Date start = DateTime.now().toDate();
    private Date end = DateTime.now().toDate();
    private Date publish = DateTime.now().toDate();

    @Before
    public void setUp() {
        course = new Course("codeTest", "nameTest", CourseType.DEV, new Author("Vasya", "Pupkin", "vasia@gmail.com"),
                start, end, publish, 10, "super description", Status.DRAFT);
    }

    @Test
    public void testGetCode() throws Exception {
        String expected = "codeTest";
        String actual = course.getCode();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCode() throws Exception {
        String expected = "codeTest1";
        course.setCode(expected);
        String actual = course.getCode();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetName() throws Exception {
        String expected = "nameTest";
        String actual = course.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetName() throws Exception {
        String expected = "nameTest1";
        course.setName(expected);
        String actual = course.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTagsList() throws Exception {
        assertNull(course.getTags());
    }

    @Test
    public void testSetTagsList() throws Exception {
        List<Tag> tagses = Arrays.asList(new Tag("Java"), new Tag("Hibernate"));
        course.setTags(tagses);
        List<Tag> actual = course.getTags();
        assertEquals(actual, tagses);
        assertTrue(actual.size() == 2);
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals(CourseType.DEV, course.getType());
    }

    @Test
    public void testSetType() throws Exception {
        course.setType(CourseType.BA);
        assertEquals(CourseType.BA, course.getType());
    }

    @Test
    public void testGetId() throws Exception {
        assertNull(course.getId());
    }

    @Test
    public void testSetId() throws Exception {
        Integer expected = 2;
        course.setId(expected);
        Integer actual = course.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testHashCode() throws Exception {
        assertNotNull(course.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertNotNull(course.toString());
        assertTrue(course.toString() != "");
    }

    @Test
    public void testEquals() throws Exception {
        Course tmp = null;
        assertFalse(course.equals(tmp));
        tmp = course;
        assertTrue(tmp.equals(course));
        assertFalse(course.equals(new Integer(1)));
        tmp = new Course("codeTest", "nameTest", CourseType.DEV, new Author("Vasya", "Pupkin", "vasia@gmail.com"),
                start, end, publish, 10, "super description", Status.DRAFT);
        assertTrue(course.equals(tmp));
        tmp = new Course();
        assertFalse(course.equals(tmp));
    }

    @Test
    public void testGetAuthor() throws Exception {
        Author expected = new Author("Vasya", "Pupkin", "vasia@gmail.com");
        Author actual = course.getAuthor();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAuthor() throws Exception {
        Author expected = new Author("Vas", "Pup", "v@gmail.com");
        course.setAuthor(expected);
        Author actual = course.getAuthor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStartDate() throws Exception {
        Date expected = start;
        Date actual = course.getStartDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetStartDate() throws Exception {
        Date expected = DateTime.now().toDate();
        course.setStartDate(expected);
        Date actual = course.getStartDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEndDate() throws Exception {
        Date expected = end;
        Date actual = course.getEndDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetEndDate() throws Exception {
        Date expected = DateTime.now().toDate();
        course.setEndDate(expected);
        Date actual = course.getEndDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPublishDate() throws Exception {
        Date expected = publish;
        Date actual = course.getPublishDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPublishDate() throws Exception {
        Date expected = DateTime.now().toDate();
        course.setPublishDate(expected);
        Date actual = course.getPublishDate();
        assertEquals(expected, actual);
    }
}
