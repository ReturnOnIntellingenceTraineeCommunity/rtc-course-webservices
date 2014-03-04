package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Tags;

import java.util.Collection;

/**
 * Tag dao interface
 *
 * @author Vladislav Pikus
 */
public interface TagsDao {

    /**
     * Save a tag in the DB
     *
     * @param tag
     * @return
     */
    Tags create(Tags tag);

    /**
     * Update a existing tag
     *
     * @param tag
     * @return
     */
    Tags update(Tags tag);

    /**
     * Check tagName exist
     *
     * @param tag
     * @return true if exist, false - not exist
     */
    boolean exist(Tags tag);

    /**
     * Delete a tag by id
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
     * Find all tags
     *
     * @return
     */
    Collection<Tags> findAll();

    /**
     * Find a tag by id
     * @param id
     * @return
     */
    Tags findById(Integer id);
}
