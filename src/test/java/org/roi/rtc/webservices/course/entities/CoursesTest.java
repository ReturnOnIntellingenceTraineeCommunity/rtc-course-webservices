package org.roi.rtc.webservices.course.entities;

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
public class CoursesTest {

    private Courses courses;

    private Date start = DateTime.now().toDate();
    private Date end = DateTime.now().toDate();

    @Before
    public void setUp() {
        courses = new Courses("codeTest", "nameTest", CourseType.DEV, new Author("Vasya", "Pupkin", "vasia@gmail.com"), start, end);
    }

    @Test
    public void testGetCode() throws Exception {
        String expected = "codeTest";
        String actual = courses.getCode();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCode() throws Exception {
        String expected = "codeTest1";
        courses.setCode(expected);
        String actual = courses.getCode();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetName() throws Exception {
        String expected = "nameTest";
        String actual = courses.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetName() throws Exception {
        String expected = "nameTest1";
        courses.setName(expected);
        String actual = courses.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTagsList() throws Exception {
        assertNull(courses.getTags());
    }

    @Test
    public void testSetTagsList() throws Exception {
        List<Tags> tagses = Arrays.asList(new Tags("Java"), new Tags("Hibernate"));
        courses.setTags(tagses);
        List<Tags> actual = courses.getTags();
        assertEquals(actual, tagses);
        assertTrue(actual.size() == 2);
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals(CourseType.DEV, courses.getType());
    }

    @Test
    public void testSetType() throws Exception {
        courses.setType(CourseType.BA);
        assertEquals(CourseType.BA, courses.getType());
    }

    @Test
    public void testGetId() throws Exception {
        assertNull(courses.getId());
    }

    @Test
    public void testSetId() throws Exception {
        Integer expected = 2;
        courses.setId(expected);
        Integer actual = courses.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testHashCode() throws Exception {
        assertNotNull(courses.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        assertNotNull(courses.toString());
        assertTrue(courses.toString() != "");
    }

    @Test
    public void testEquals() throws Exception {
        Courses tmp = null;
        assertFalse(courses.equals(tmp));
        tmp = courses;
        assertTrue(tmp.equals(courses));
        assertFalse(courses.equals(new Integer(1)));
        tmp = new Courses("codeTest", "nameTest", CourseType.DEV, new Author("Vasya", "Pupkin", "vasia@gmail.com"), start, end);
        assertTrue(courses.equals(tmp));
        tmp = new Courses();
        assertFalse(courses.equals(tmp));
    }

    @Test
    public void testGetAuthor() throws Exception {
        Author expected = new Author("Vasya", "Pupkin", "vasia@gmail.com");
        Author actual = courses.getAuthor();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAuthor() throws Exception {
        Author expected = new Author("Vas", "Pup", "v@gmail.com");
        courses.setAuthor(expected);
        Author actual = courses.getAuthor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStartDate() throws Exception {
        Date expected = start;
        Date actual = courses.getStartDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetStartDate() throws Exception {
        Date expected = DateTime.now().toDate();
        courses.setStartDate(expected);
        Date actual = courses.getStartDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEndDate() throws Exception {
        Date expected = end;
        Date actual = courses.getEndDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetEndDate() throws Exception {
        Date expected = DateTime.now().toDate();
        courses.setEndDate(expected);
        Date actual = courses.getEndDate();
        assertEquals(expected, actual);
    }
}
