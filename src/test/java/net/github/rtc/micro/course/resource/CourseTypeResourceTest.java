package net.github.rtc.micro.course.resource;

import com.yammer.dropwizard.testing.ResourceTest;
import org.junit.After;
import org.junit.Test;
import net.github.rtc.micro.course.entities.CourseType;

import java.util.Arrays;
import java.util.Collection;

import static com.yammer.dropwizard.testing.JsonHelpers.asJson;
import static org.junit.Assert.assertEquals;

/**
 * @author Vladislav Pikus
 */
public class CourseTypeResourceTest extends ResourceTest {

    @After
    public void tearDown() throws Exception {
        this.tearDownJersey();
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<CourseType> courseTypes = Arrays.asList(CourseType.values());
        String actual = client().resource("/course_type").get(String.class);
        assertEquals(asJson(courseTypes), actual);
    }

    @Override
    protected void setUpResources() throws Exception {
        addResource(new CourseTypeResource());
    }
}
