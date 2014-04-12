package net.github.rtc.micro.course.dao;

import net.github.rtc.micro.course.entities.Course;
import net.github.rtc.micro.course.model.dto.CourseSearchCriteriaDto;
import net.github.rtc.micro.course.model.dto.PageDto;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link net.github.rtc.micro.course.entities.Course} objects
 *
 * @author Vladislav Pikus
 */
public interface CourseDao {

    /**
     * Save a new course in the DB
     *
     * @param course course for save
     * @return saved course
     */
    Course create(Course course);

    /**
     * Update an existing course
     *
     * @param course course for update
     * @return updated course
     */
    Course update(Course course);

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
    Collection<Course> findAll();

    /**
     * Find a course by id
     *
     * @param id course id
     * @return course
     */
    Course findById(Integer id);

    /**
     * Find collection of courses by filter parameters. Such as (title, category, tags, startDate)
     * And find with pagination criteria
     *
     * @param filter filter object
     * @param pageDto pageDto object
     * @return collection of courses
     */
    Collection<Course> findByCriteria(CourseSearchCriteriaDto filter, PageDto pageDto);

    /**
     * Find course object by code
     *
     * @param code course code
     * @return course object
     */
    Course findByCode(String code);

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
    Course merge(Course course);

    /**
     * Find total course count by filter parameters. Such as (title, category, tags, startDate)
     * And find with pagination criteria
     *
     * @param filter filter object
     * @return total course count
     */
    Integer getCount(CourseSearchCriteriaDto filter);
}
