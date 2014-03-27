package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Courses;
import org.roi.rtc.webservices.course.model.CourseFilter;
import org.roi.rtc.webservices.course.model.Page;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link Courses} objects
 *
 * @author Vladislav Pikus
 */
public interface CoursesDao {

    /**
     * Save a new course in the DB
     *
     * @param course course for save
     * @return saved course
     */
    Courses create(Courses course);

    /**
     * Update an existing course
     *
     * @param course course for update
     * @return updated course
     */
    Courses update(Courses course);

    /**
     * Check course code exist
     *
     * @param code course code
     * @return true if exist, false - not exist
     */
    boolean exist(String code);

    /**
     * Delete an existing course by id
     *
     * @param id course id
     */
    void delete(Integer id);

    /**
     * Delete all courses
     */
    void deleteAll();

    /**
     * Get courses count
     *
     * @return courses count
     */
    Integer getCount();

    /**
     * Find collection of courses in the DB
     *
     * @return collection of courses
     */
    Collection<Courses> findAll();

    /**
     * Find a course by id
     *
     * @param id course id
     * @return course
     */
    Courses findById(Integer id);

    /**
     * Find collection of courses by filter parameters. Such as (title, category, tags, startDate)
     * And find with pagination criteria
     *
     * @param filter filter object
     * @param page page object
     * @return collection of courses
     */
    Collection<Courses> findByFilter(CourseFilter filter, Page page);

    /**
     * Find course object by code
     *
     * @param code course code
     * @return course object
     */
    Courses findByCode(String code);

    /**
     * Delete course by code
     *
     * @param code course code
     */
    void delete(String code);

    /**
     * SaveOrUpdate with merge associated entities
     *
     * @param course course object
     * @return course object with updated keys
     */
    Courses merge(Courses course);
}
