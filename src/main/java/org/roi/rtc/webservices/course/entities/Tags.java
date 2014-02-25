package org.roi.rtc.webservices.course.entities;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * @author Vladislav Pikus
 */
@Entity
@Table(name = "tags_db",uniqueConstraints = @UniqueConstraint(columnNames="value"))
public class Tags {

    private Integer id;

    @NotEmpty
    private String value;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 30, unique = true)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Tags(String value) {
        this.value = value;
    }

    public Tags() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tags tags = (Tags) o;

        if (id != null ? !id.equals(tags.id) : tags.id != null) return false;
        if (value != null ? !value.equals(tags.value) : tags.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tags{");
        sb.append("id=").append(id);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
