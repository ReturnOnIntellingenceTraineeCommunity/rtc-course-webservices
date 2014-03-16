package org.roi.rtc.webservices.course.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Filter model for {@link org.roi.rtc.webservices.course.entities.Courses}
 * This model use in {@link org.roi.rtc.webservices.course.dao.impl.CoursesDaoImpl}
 * for filtering rows
 *
 * @author Vladislav Pikus
 */
public class CourseFilter {
    private String title;
    private Date startDate;
    private Collection<String> categories;
    private Collection<String> tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Collection<String> getCategories() {
        return categories;
    }

    public void setCategories(Collection<String> categories) {
        this.categories = categories;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public CourseFilter() {

    }

    public CourseFilter(String title, Date startDate, Collection<String> categories, Collection<String> tags) {
        this.title = title;
        this.startDate = startDate;
        this.categories = categories;
        this.tags = tags;
    }

    public CourseFilter(Builder builder) {
        title = builder.title;
        startDate = builder.startDate;
        categories = builder.categories;
        tags = builder.tags;
    }

    /**
     * Builder for {@link CourseFilter}. The builder simplifies the creation of objects
     * and makes the code more understandable
     */
    public static class Builder {
        private String title = "";
        private Date startDate;
        private Collection<String> categories = new ArrayList<String>();
        private Collection<String> tags = new ArrayList<String>();

        public Builder title(String val) {
            title = val;
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

        public CourseFilter build() {
            return new CourseFilter(this);
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseFilter{");
        sb.append("title='").append(title).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", categories=").append(categories);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}
