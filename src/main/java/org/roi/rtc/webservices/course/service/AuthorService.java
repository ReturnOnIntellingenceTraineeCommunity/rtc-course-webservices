package org.roi.rtc.webservices.course.service;

import org.roi.rtc.webservices.course.entities.Author;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 18.02.14
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
public interface AuthorService {
    Author saveOrUpdate(Author author);

    void delete(Integer id);

    Integer getCount();

    Collection<Author> findAll();

    Author findById(Integer id);
}
