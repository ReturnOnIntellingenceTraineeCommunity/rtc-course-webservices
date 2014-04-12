package net.github.rtc.micro.course.model;

import net.github.rtc.micro.course.model.dto.PageDto;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Vladislav Pikus
 */
public class PageDtoTest {
    private PageDto pageDto;

    @Before
    public void setUp() throws Exception {
        pageDto = new PageDto.Builder(10).page(0).maxResult(7).build();
    }

    @Test
    public void testGetFirstResult() throws Exception {
        Integer expected = 0;
        Integer actual = pageDto.getFirstResult();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetMaxResult() throws Exception {
        Integer expected = 7;
        Integer actual = pageDto.getMaxResult();
        assertEquals(expected, actual);
    }

    @Test
    public void testToString() throws Exception {
        pageDto = new PageDto(0, 10);
        assertNotNull(pageDto.toString());
    }
}
