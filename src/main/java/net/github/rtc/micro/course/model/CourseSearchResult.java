package net.github.rtc.micro.course.model;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import net.github.rtc.micro.course.entities.Course;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
@XmlRootElement
public class CourseSearchResult {
    private final Collection<Course> courses;
    private final int totalCount;
    private final int offset;
    private final int limit;

    public CourseSearchResult(Builder builder) {
        this.courses = builder.courses;
        this.totalCount = builder.totalCount;
        this.offset = builder.offset;
        this.limit = builder.limit;
    }

    public Collection<Course> getCourses() {
        return courses;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    /**
     * Builder for {@link CourseSearchResult}. The builder simplifies the creation of objects
     * and makes the code more understandable
     */
    @JsonIgnoreType
    public static class Builder {
        private Collection<Course> courses = new ArrayList<Course>();
        private int totalCount;
        private int offset;
        private int limit;

        public Builder courses(Collection<Course> courses) {
            this.courses = courses;
            return this;
        }

        public Builder totalCount(int totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public CourseSearchResult build() {
            return new CourseSearchResult(this);
        }
    }
}
