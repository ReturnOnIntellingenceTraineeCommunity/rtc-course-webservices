package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Author;

import java.util.Collection;

/**
 * Author dao interface
 *
 * @author Vladislav Pikus
 */
public interface AuthorDao {

    /**
     * Save a new author in the DB
     *
     * @param author author for save
     * @return saved author with id
     */
    Author create(Author author);

    /**
     * Update an existing author
     *
     * @param author
     * @return
     */
    Author update(Author author);

    /**
     * Delete a author by id
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
     * @return row count
     */
    Integer getCount();

    /**
     * Find all author in the DB
     *
     * @return
     */
    Collection<Author> findAll();

    /**
     * Find a author by id
     * @param id identifier
     * @return find obj
     */
    Author findById(Integer id);
}
