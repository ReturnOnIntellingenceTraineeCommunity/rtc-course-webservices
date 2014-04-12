package net.github.rtc.micro.course.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import net.github.rtc.micro.course.dao.AuthorDao;
import net.github.rtc.micro.course.entities.Author;

import java.util.Collection;

/**
 * Data Access Object Implementation
 * Extend by {@link AbstractDAO}
 *
 * @author Vladislav Pikus
 */
public class AuthorDaoImpl extends AbstractDAO<Author> implements AuthorDao {
    public AuthorDaoImpl(SessionFactory factory) {
        super(factory);
    }

    /**
     * @see AuthorDao#create(Author)
     */
    @Override
    public Author create(Author author) {
        currentSession().save(author);
        return author;
    }

    /**
     * @see AuthorDao#update(Author)
     */
    @Override
    public Author update(Author author) {
        currentSession().update(author);
        return author;
    }

    /**
     * @see AuthorDao#delete(Integer)
     */
    @Override
    public void delete(Integer id) {
        Author author = (Author) currentSession().get(Author.class, id);
        if (author != null) {
            currentSession().delete(author);
        }
    }

    /**
     * @see AuthorDao#getCount()
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Author.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * @see AuthorDao#findAll()
     */
    @Override
    public Collection<Author> findAll() {
        return currentSession().createCriteria(Author.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * @see AuthorDao#get(Integer)
     */
    @Override
    public Author get(Integer id) {
        return (Author) currentSession().get(Author.class, id);
    }

    /**
     * @see AuthorDao#deleteAll()
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Author");
    }

    /**
     * @see AuthorDao#findByEmail(String)
     */
    @Override
    public Author findByEmail(String email) {
        return (Author) currentSession().createCriteria(Author.class).add(Restrictions.eq("email", email))
                .uniqueResult();
    }
}
