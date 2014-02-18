package org.roi.rtc.webservices.course.service.impl;

import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.roi.rtc.webservices.course.entities.Author;
import org.roi.rtc.webservices.course.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 18.02.14
 * Time: 15:14
 * To change this template use File | Settings | File Templates.
 */
@Service("authorService")
@Transactional
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    public void setDao(AuthorDao dao) {
        this.dao = dao;
    }

    private AuthorDao dao;

    @Override
    public Author saveOrUpdate(Author author) {
        return dao.saveOrUpdate(author);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCount() {
        return dao.getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Author> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Integer id) {
        return dao.findById(id);
    }
}
