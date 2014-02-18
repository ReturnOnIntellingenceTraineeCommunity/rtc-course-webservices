package org.roi.rtc.webservices.course.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.roi.rtc.webservices.course.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session currentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Author saveOrUpdate(Author author) {
        currentSession().saveOrUpdate(author);
        return author;
    }

    @Override
    public void delete(Integer id) {
        Author author = (Author) currentSession().get(Author.class, id);
        if (author != null) {
            currentSession().delete(author);
        }
    }

    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Author.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    @Override
    public Collection<Author> findAll() {
        return currentSession().createCriteria(Author.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Author findById(Integer id) {
        return (Author) currentSession().get(Author.class, id);
    }
}
