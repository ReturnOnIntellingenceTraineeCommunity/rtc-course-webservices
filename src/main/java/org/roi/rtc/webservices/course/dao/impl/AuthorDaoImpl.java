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
 * Author dao implementation
 *
 * @author Vladislav Pikus
 */
public class AuthorDaoImpl extends AbstractDAO<Author> implements AuthorDao {

    /**
     * Constructor
     * @param factory session factory
     */
    public AuthorDaoImpl(SessionFactory factory) {
        super(factory);
    }

    /**
     * Create a new author in the DB
     *
     * @param author author for save
     * @return saved author with id
     */
    @Override
    public Author create(Author author) {
        currentSession().save(author);
        return author;
    }

    /**
     * Update an existing author
     *
     * @param author
     * @return
     */
    @Override
    public Author update(Author author) {
        currentSession().update(author);
        return author;
    }

    /**
     * Delete a author by id
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Author author = (Author) currentSession().get(Author.class, id);
        if (author != null) {
            currentSession().delete(author);
        }
    }

    /**
     * Get row count
     *
     * @return row count
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Author.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * Find all author in the DB
     *
     * @return
     */
    @Override
    public Collection<Author> findAll() {
        return currentSession().createCriteria(Author.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * Find a author by id
     * @param id identifier
     * @return find obj
     */
    @Override
    public Author findById(Integer id) {
        return (Author) currentSession().get(Author.class, id);
    }

    /**
     * Delete all rows
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Author");
    }
}
