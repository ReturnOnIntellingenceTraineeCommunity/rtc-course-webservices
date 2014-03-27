package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Tags;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link Tags} objects
 *
 * @author Vladislav Pikus
 */
public interface TagsDao {

    /**
     * Save a tag in the DB
     *
     * @param tag tag for save
     * @return saved tag
     */
    Tags create(Tags tag);

    /**
     * Update a existing tag
     *
     * @param tag tag for update
     * @return updated tag
     */
    Tags update(Tags tag);

    /**
     * Check tag name exist
     *
     * @param tag tag for check
     * @return true if exist, false - not exist
     */
    boolean exist(Tags tag);

    /**
     * Delete a tag by id
     *
     * @param id tag id
     */
    void delete(Integer id);

    /**
     * Delete all tags
     */
    void deleteAll();

    /**
     * Get author count
     *
     * @return author count
     */
    Integer getCount();

    /**
     * Find collection of tags
     *
     * @return collection of tags
     */
    Collection<Tags> findAll();

    /**
     * Find a tag by id
     * @param id tag id
     * @return tag
     */
    Tags findById(Integer id);

    /**
     * Find tags object by value
     *
     * @param value value of the tags
     * @return tags object
     */
    Tags findByValue(String value);
}
