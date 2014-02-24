package org.roi.rtc.webservices.course.entities;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author Eugene Lapshin
 */
@Entity
@Table(name = "courses_db")
public class Courses {
private Integer id;

// первый вариант
//    @NotEmpty
//    @Size(min = 2, max = 30)
//    private String code, name, category, curator, start, end;

// второй вариант
    @NotEmpty
    @Size(min = 2, max = 30)
    private String code;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String category;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String curator;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String start;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String end;

    @Column(length = 30)
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    @Column(length = 30)
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Column(length = 30)
    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    @Column(length = 30)
    public String getCurator() { return curator; }

    public void setCurator(String curator) { this.curator = curator; }

    @Column(length = 30)
    public String getStart() { return start; }

    public void setStart(String start) { this.start = start; }

    @Column(length = 30)
    public String getEnd() { return end; }

    public void setEnd(String end) { this.end = end; }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Courses() {

    }

    public Courses(String code, String name, String category, String curator, String start, String end) {
        this.code = code;
        this.name = name;
        this.category = category;
        this.curator = curator;
        this.start = start;
        this.end = end;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Courses courses = (Courses) o;
        if (code != null ? !code.equals(courses.code) : courses.code != null) return false;
        if (name != null ? !name.equals(courses.name) : courses.name != null) return false;
        if (category != null ? !category.equals(courses.category) : courses.category != null) return false;
        if (curator != null ? !curator.equals(courses.curator) : courses.curator != null) return false;
        if (start != null ? !start.equals(courses.start) : courses.start != null) return false;
        if (end != null ? !end.equals(courses.end) : courses.end != null) return false;

        return true;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (curator != null ? curator.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Courses{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", start='").append(start).append('\'');
        sb.append(", end='").append(end).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
