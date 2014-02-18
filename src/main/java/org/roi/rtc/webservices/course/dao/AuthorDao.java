package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Author;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
public interface AuthorDao {
    Author saveOrUpdate(Author author);

    void delete(Integer id);

    Integer getCount();

    Collection<Author> findAll();

    Author findById(Integer id);
}
