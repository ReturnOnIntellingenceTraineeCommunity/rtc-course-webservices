package org.roi.rtc.webservices.course.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Eugene Lapshin
 */
public class CoursesTest {

    private Courses courses;

    @Before
    public void setUp() {
        courses = new Courses("codeTest", "nameTest", "categoryTest", "curatorTest", "startTest", "endTest");
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
    public void testGetCategory() throws Exception {
        String expected = "categoryTest";
        String actual = courses.getCategory();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCategory() throws Exception {
        String expected = "categoryTest1";
        courses.setCategory(expected);
        String actual = courses.getCategory();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCurator() throws Exception {
        String expected = "curatorTest";
        String actual = courses.getCurator();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCurator() throws Exception {
        String expected = "curatorTest1";
        courses.setCurator(expected);
        String actual = courses.getCurator();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStart() throws Exception {
        String expected = "startTest";
        String actual = courses.getStart();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetStart() throws Exception {
        String expected = "startTest1";
        courses.setStart(expected);
        String actual = courses.getStart();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEnd() throws Exception {
        String expected = "endTest";
        String actual = courses.getEnd();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetEnd() throws Exception {
        String expected = "endTest1";
        courses.setEnd(expected);
        String actual = courses.getEnd();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetId() throws Exception {
        assertNull(courses.getId());
    }

    @Test
    public void testSetId() throws Exception {
        Integer expected = 10;
        courses.setId(expected);
        Integer actual = courses.getId();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() throws Exception {
        Courses tmp = null;
        assertFalse(courses.equals(tmp));
        assertFalse(courses.equals(new Integer(1)));
        tmp = courses;
        assertTrue(courses.equals(tmp));
        tmp = new Courses ("codeTest", "nameTest", "categoryTest", "curatorTest", "startTest", "endTest");
        assertTrue(courses.equals(tmp));
        tmp = new Courses();
        assertFalse(courses.equals(tmp));
    }

    @Test
    public void testHashCode() throws Exception {
        assertNotNull(courses.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        //"codeTest", "nameTest", "categoryTest", "curatorTest", "startTest", "endTest");
        String str = "Courses{id=null, code='codeTest', name='nameTest', category='categoryTest', start='startTest', end='endTest'}";
        assertEquals(str, courses.toString());
    }

}
