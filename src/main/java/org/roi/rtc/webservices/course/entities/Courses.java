package org.roi.rtc.webservices.course.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author Eugene Lapshin
 */
@Entity
@Table(name = "courses_db",uniqueConstraints = @UniqueConstraint(columnNames="code"))
public class Courses {
    private Integer id;

    @NotEmpty
    private String code;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    private CourseType type;

    @NotNull
    private Author author;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    private List<Tags> tagsList;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name="courses_tags",
            joinColumns={@JoinColumn(name="tagId")},
            inverseJoinColumns={@JoinColumn(name="id")})
    public List<Tags> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<Tags> tagsList) {
        this.tagsList = tagsList;
    }

    @Column
    @Enumerated(EnumType.ORDINAL)
    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "AUTHOR_ID")
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(length = 38)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Courses(String code, String name, CourseType type, Author author, Date startDate, Date endDate) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.author = author;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Courses courses = (Courses) o;

        if (author != null ? !author.equals(courses.author) : courses.author != null) return false;
        if (code != null ? !code.equals(courses.code) : courses.code != null) return false;
        if (endDate != null ? !endDate.equals(courses.endDate) : courses.endDate != null) return false;
        if (id != null ? !id.equals(courses.id) : courses.id != null) return false;
        if (name != null ? !name.equals(courses.name) : courses.name != null) return false;
        if (startDate != null ? !startDate.equals(courses.startDate) : courses.startDate != null) return false;
        if (tagsList != null ? !tagsList.equals(courses.tagsList) : courses.tagsList != null) return false;
        if (type != courses.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (tagsList != null ? tagsList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Courses{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", author=").append(author);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", tagsList=").append(tagsList);
        sb.append('}');
        return sb.toString();
    }
}
