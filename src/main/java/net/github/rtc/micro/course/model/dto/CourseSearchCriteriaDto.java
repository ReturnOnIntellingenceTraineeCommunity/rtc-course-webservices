package net.github.rtc.micro.course.model.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Filter model for {@link net.github.rtc.micro.course.entities.Course}
 * This model use in {@link net.github.rtc.micro.course.dao.impl.CourseDaoImpl}
 * for filtering rows
 *
 * @author Vladislav Pikus
 */
public final class CourseSearchCriteriaDto {
    private String title;
    private Date startDate;
    private Collection<String> categories;
    private Collection<String> tags;
    private String status;

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Collection<String> getCategories() {
        return categories;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public CourseSearchCriteriaDto() {

    }

    public CourseSearchCriteriaDto(String title, Date startDate, Collection<String> categories, Collection<String> tags, String status) {
        this.title = title;
        this.startDate = startDate;
        this.categories = categories;
        this.tags = tags;
        this.status = status;
    }

    public CourseSearchCriteriaDto(Builder builder) {
        title = builder.title;
        startDate = builder.startDate;
        categories = builder.categories;
        tags = builder.tags;
        status= builder.status;
    }

    /**
     * Builder for {@link CourseSearchCriteriaDto}. The builder simplifies the creation of objects
     * and makes the code more understandable
     */
    public static class Builder {
        private String title = "";
        private Date startDate;
        private Collection<String> categories = new ArrayList<String>();
        private Collection<String> tags = new ArrayList<String>();
        private String status;

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder startDate(String val) {
            if (val != null && !val.equals("")) {
                final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    startDate = dateFormat.parse(val);
                } catch (ParseException e) {
                    startDate = null;
                }
            }
            return this;
        }

        public Builder categories(String val) {
            if (val != null) {
                final String[] cats = val.split(";");
                for (String cat : cats) {
                    if (!cat.equals("")) {
                        categories.add(cat);
                    }
                }
            }
            return this;
        }

        public Builder tags(String val) {
            if (val != null) {
                final String[] tagS = val.split(";");
                for (String tag : tagS) {
                    if (!tag.equals("")) {
                        tags.add(tag);
                    }
                }
            }
            return this;
        }

        public CourseSearchCriteriaDto build() {
            return new CourseSearchCriteriaDto(this);
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseSearchCriteriaDto{");
        sb.append("title='").append(title).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", categories=").append(categories);
        sb.append(", tags=").append(tags);
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
