package org.roi.rtc.webservices.course.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Course entity

 *
 * @author Eugene Lapshin
 * @author Vladislav Pikus
 */
@Entity
@Table(name = "courses_db",uniqueConstraints = @UniqueConstraint(columnNames="code"))
@XmlRootElement
public class Courses implements Serializable {
    @JsonIgnore
    private Integer id;

    @NotEmpty
    private String code;

    @NotEmpty
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    private CourseType type;

    @NotNull
    private Author author;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    private Date publishDate;

    private List<Tags> tags;

    @NotNull
    @Size(min = 1)
    private Integer capacity;

    @NotNull
    @Size(max = 255)
    private String description;

    @NotNull
    private Status status;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name="courses_tags",
            joinColumns={@JoinColumn(name="tagId")},
            inverseJoinColumns={@JoinColumn(name="id")})
    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
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

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPublishDate() { return publishDate; }

    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    @Column
    public Integer getCapacity() { return capacity;}

    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    @Column(length = 255)
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Column
    @Enumerated(EnumType.STRING)
    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public Courses() {

    }

    public Courses(String code, String name, CourseType type, Author author, Date startDate,
                   Date endDate, Date publishDate, Integer capacity, String description, Status status) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.author = author;
        this.startDate = startDate;
        this.endDate = endDate;
        this.publishDate = publishDate;
        this.capacity = capacity;
        this.description = description;
        this.status = status;
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
        if (publishDate != null ? !publishDate.equals(courses.publishDate) : courses.publishDate != null) return false;
        if (tags != null ? !tags.equals(courses.tags) : courses.tags != null) return false;
        if (description != null ? !description.equals(courses.description) : courses.description != null) return false;
        if (capacity != null ? !capacity.equals(courses.capacity) : courses.capacity != null) return false;
        if (status != courses.status) return false;
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
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
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
        sb.append(", capacity=").append(capacity);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", publishDate=").append(publishDate);
        sb.append(", tags=").append(tags);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }


}
