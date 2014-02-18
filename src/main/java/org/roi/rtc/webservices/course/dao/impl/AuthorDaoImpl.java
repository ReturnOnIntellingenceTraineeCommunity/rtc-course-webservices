package org.roi.rtc.webservices.course.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.roi.rtc.webservices.course.entities.Author;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
public class AuthorDaoImpl extends AbstractDAO<Author> implements AuthorDao {
    public AuthorDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Author saveOrUpdate(Author author) {
        return persist(author);
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
