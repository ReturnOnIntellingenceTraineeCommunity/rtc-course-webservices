package net.github.rtc.micro.course.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vladislav Pikus
 */
public class AuthorTest {
    private Author author;

    @Before
    public void setUp() {
        author = new Author("Ivan", "Pupkin", "pupkin.ivan.gmail.com");
    }

    @Test
    public void testGetFirstName() throws Exception {
        String expected = "Ivan";
        String actual = author.getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetFirstName() throws Exception {
        String expected = "Valera";
        author.setFirstName(expected);
        String actual = author.getFirstName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetLastName() throws Exception {
        String expected = "Pupkin";
        String actual = author.getLastName();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetLastName() throws Exception {
        String expected = "IvanOff";
        author.setLastName(expected);
        String actual = author.getLastName();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetEmail() throws Exception {
        String expected = "pupkin.ivan.gmail.com";
        String actual = author.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetEmail() throws Exception {
        String expected = "valera.gmail.com";
        author.setEmail(expected);
        String actual = author.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetId() throws Exception {
        assertNull(author.getId());
    }

    @Test
    public void testSetId() throws Exception {
        Integer expected = 10;
        author.setId(expected);
        Integer actual = author.getId();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testEquals() throws Exception {
        Author tmp = null;
        assertFalse(author.equals(tmp));
        assertFalse(author.equals(new Integer(1)));
        tmp = author;
        assertTrue(author.equals(tmp));
        tmp = new Author("Ivan", "Pupkin", "pupkin.ivan.gmail.com");
        assertTrue(author.equals(tmp));
        tmp = new Author();
        assertFalse(author.equals(tmp));
    }

    @Test
    public void testHashCode() throws Exception {
        assertNotNull(author.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        String str = "Author{id=null, firstName='Ivan', lastName='Pupkin', email='pupkin.ivan.gmail.com'}";
        assertEquals(str, author.toString());
    }
}
