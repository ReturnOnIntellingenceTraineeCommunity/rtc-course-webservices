package org.roi.rtc.webservices.course.model;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Vladislav Pikus
 */
public class CourseFilterTest {
    private CourseFilter filter;

    @Before
    public void setUp() throws Exception {
        filter = new CourseFilter.Builder().title("Java Practice").startDate("11-11-2011")
                .categories("DEV;BA;QA").tags("Java;Spring;Hibernate;JPA").build();
    }

    @Test
    public void testGetTitle() throws Exception {
        String expected = "Java Practice";
        String actual = filter.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetTitle() throws Exception {
        String expected = "New Name";
        filter.setTitle("New Name");
        String actual = filter.getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetStartDate() throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expected;
        try {
            expected = dateFormat.parse("11-11-2011");
        } catch (ParseException e) {
            expected = null;
        }
        Date actual = filter.getStartDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetStartDate() throws Exception {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expected;
        try {
            expected = dateFormat.parse("11-10-2010");
        } catch (ParseException e) {
            expected = null;
        }
        filter.setStartDate(expected);
        Date actual = filter.getStartDate();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCategories() throws Exception {
        Collection<String> expected = Arrays.asList("DEV", "BA", "QA");
        Collection<String> actual = filter.getCategories();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCategories() throws Exception {
        Collection<String> expected = Arrays.asList("DEV", "BA", "QA", "NEW");
        filter.setCategories(expected);
        Collection<String> actual = filter.getCategories();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTags() throws Exception {
        Collection<String> expected = Arrays.asList("Java", "Spring", "Hibernate", "JPA");
        Collection<String> actual = filter.getTags();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetTags() throws Exception {
        Collection<String> expected = Arrays.asList("Java", "Spring", "Hibernate", "JPA", "New");
        filter.setTags(expected);
        Collection<String> actual = filter.getTags();
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() throws Exception {
        assertNotNull(filter.toString());
    }
}
