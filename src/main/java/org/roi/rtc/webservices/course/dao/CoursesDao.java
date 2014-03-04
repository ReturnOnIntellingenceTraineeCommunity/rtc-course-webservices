package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Courses;

import java.util.Collection;

/**
 * Course dao interface
 *
 * @author Vladislav Pikus
 */
public interface CoursesDao {

    /**
     * Save a new course in the DB
     *
     * @param course
     * @return
     */
    Courses create(Courses course);

    /**
     * Update an existing course
     *
     * @param course
     * @return
     */
    Courses update(Courses course);

    /**
     * Check code exist
     *
     * @param code code
     * @return true if exist, false - not exist
     */
    boolean exist(String code);

    /**
     * Delete an existing course by id
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * Delete all rows
     */
    void deleteAll();

    /**
     * Get row count
     *
     * @return
     */
    Integer getCount();

    /**
     * Find all courses in the DB
     *
     * @return
     */
    Collection<Courses> findAll();

    /**
     * Find a course by id
     *
     * @param id
     * @return
     */
    Courses findById(Integer id);
}
