package org.roi.rtc.webservices.course.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vladislav Pikus
 */
public class TagsTest {

    private Tags tag;

    @Before
    public void setUp() throws Exception {
        tag = new Tags("C#");
        tag.setId(1);
    }

    @Test
    public void testGetId() throws Exception {
        Integer expected = 1;
        Integer actual = tag.getId();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetId() throws Exception {
        Integer expected = 2;
        tag.setId(expected);
        Integer actual = tag.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void testGetValue() throws Exception {
        String expected = "C#";
        String actual = tag.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetValue() throws Exception {
        String expected = "Java";
        tag.setValue(new String(expected));
        String actual = tag.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() throws Exception {
        Tags tmp = null;
        assertFalse(tag.equals(tmp));
        assertFalse(tag.equals("1"));
        tmp = tag;
        assertTrue(tag.equals(tmp));
        tmp = new Tags("C#");
        tmp.setId(1);
        assertTrue(tag.equals(tmp));
        tmp = new Tags();
        tmp.setId(1);
        assertFalse(tag.equals(tmp));
    }

    @Test
    public void testHashCode() throws Exception {
        assertNotNull(tag.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        String expected = "Tags{id=1, value='C#'}";
        String actual = tag.toString();
        assertEquals(expected, actual);
    }
}
