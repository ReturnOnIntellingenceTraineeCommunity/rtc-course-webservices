package net.github.rtc.micro.course.model;

import net.github.rtc.micro.course.model.dto.CourseSearchCriteriaDto;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Vladislav Pikus
 */
public class CourseSearchCriteriaDtoTest {
    private CourseSearchCriteriaDto filter;

    @Before
    public void setUp() throws Exception {
        filter = new CourseSearchCriteriaDto.Builder().title("Java Practice").startDate("11-11-2011")
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
        String expected = "Java Practice";
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
            expected = dateFormat.parse("11-11-2011");
        } catch (ParseException e) {
            expected = null;
        }
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
        Collection<String> expected = Arrays.asList("DEV", "BA", "QA");
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
        Collection<String> expected = Arrays.asList("Java", "Spring", "Hibernate", "JPA");
        Collection<String> actual = filter.getTags();
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() throws Exception {
        assertNotNull(filter.toString());
    }

    @Test
    public void testTestDefConstr() throws Exception {
        filter = new CourseSearchCriteriaDto();
        assertNull(filter.getStartDate());
        assertNull(filter.getTitle());
        assertNull(filter.getCategories());
        assertNull(filter.getTitle());
    }

    @Test
    public void testConstrWithParam() throws Exception {
        Collection<String> tags = Arrays.asList("Java", "Spring", "Hibernate", "JPA", "New");
        Collection<String> cat = Arrays.asList("Java", "Spring", "Hibernate", "JPA");
        String title = "title";
        String status = "DRAFT";
        Date date = DateTime.now().toDate();
        filter = new CourseSearchCriteriaDto(title, date, cat, tags, status);
        assertEquals(title, filter.getTitle());
        assertEquals(date, filter.getStartDate());
        assertEquals(cat, filter.getCategories());
        assertEquals(tags, filter.getTags());
    }

    @Test
    public void testDateBuildError() throws Exception {
        filter = new CourseSearchCriteriaDto.Builder().startDate("1111").build();
        assertNull(filter.getStartDate());
    }
}
