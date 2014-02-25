package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Courses;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
public interface CoursesDao {
    Courses create(Courses course);

    Courses update(Courses course);

    boolean exist(String code);

    void delete(Integer id);

    void deleteAll();

    Integer getCount();

    Collection<Courses> findAll();

    Courses findById(Integer id);
}
