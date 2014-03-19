package org.roi.rtc.webservices.course.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Vladislav Pikus
 */
public class PageTest {
    private Page page;

    @Before
    public void setUp() throws Exception {
        page = new Page.Builder(10).page(0).maxResult(7).build();
    }

    @Test
    public void testGetFirstResult() throws Exception {
        Integer expected = 0;
        Integer actual = page.getFirstResult();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMaxResult() throws Exception {
        Integer expected = 7;
        Integer actual = page.getMaxResult();
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() throws Exception {
        page = new Page(0, 10);
        assertNotNull(page.toString());
    }
}
