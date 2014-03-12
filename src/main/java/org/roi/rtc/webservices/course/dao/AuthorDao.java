package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Author;

import java.util.Collection;

/**
 * Data Access Object Interface
 * Provides CRUD operations with {@link Author} objects
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
     * @param author    author for update
     * @return updated author
     */
    Author update(Author author);

    /**
     * Delete a author by id
     *
     * @param id author id
     */
    void delete(Integer id);

    /**
     * Delete all authors
     */
    void deleteAll();

    /**
     * Get authors count
     *
     * @return authors count
     */
    Integer getCount();

    /**
     * Find collection of author in the DB
     *
     * @return collection of author
     */
    Collection<Author> findAll();

    /**
     * Find a author by id
     *
     * @param id author id
     * @return author
     */
    Author findById(Integer id);
}
