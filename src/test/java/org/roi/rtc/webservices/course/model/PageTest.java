package org.roi.rtc.webservices.course.model;

import org.junit.Before;
import org.junit.Test;

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

    }

    @Test
    public void testGetMaxResult() throws Exception {

    }

    @Test
    public void testToString() throws Exception {

    }
}
