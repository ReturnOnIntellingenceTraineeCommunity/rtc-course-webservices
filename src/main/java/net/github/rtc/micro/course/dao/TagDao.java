package net.github.rtc.micro.course.dao;

import net.github.rtc.micro.course.entities.Tag;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link net.github.rtc.micro.course.entities.Tag} objects
 *
 * @author Vladislav Pikus
 */
public interface TagDao {

    /**
     * Save a tag in the DB
     *
     * @param tag tag for save
     * @return saved tag
     */
    Tag create(Tag tag);

    /**
     * Update a existing tag
     *
     * @param tag tag for update
     * @return updated tag
     */
    Tag update(Tag tag);

    /**
     * Check tag name exist
     *
     * @param tag tag for check
     * @return true if exist, false - not exist
     */
    boolean exist(Tag tag);

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
    Collection<Tag> findAll();

    /**
     * Find a tag by id
     * @param id tag id
     * @return tag
     */
    Tag get(Integer id);

    /**
     * Find tags object by value
     *
     * @param value value of the tags
     * @return tags object
     */
    Tag findByValue(String value);
}
