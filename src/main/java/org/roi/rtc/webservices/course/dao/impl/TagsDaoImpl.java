package org.roi.rtc.webservices.course.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.roi.rtc.webservices.course.dao.TagsDao;
import org.roi.rtc.webservices.course.entities.Tags;

import java.util.Collection;

/**
 * Data Access Object Implementation
 * Extend by {@link AbstractDAO}
 *
 * @author Vladislav Pikus
 */
public class TagsDaoImpl extends AbstractDAO<Tags> implements TagsDao {
    /**
     * Constructor
     *
     * @param factory session factory
     */
    public TagsDaoImpl(SessionFactory factory) {
        super(factory);
    }

    /**
     * @see TagsDao#create(Tags)
     */
    @Override
    public Tags create(Tags tag) {
        currentSession().save(tag);
        return tag;
    }

    /**
     * @see TagsDao#update(Tags)
     */
    @Override
    public Tags update(Tags tag) {
        currentSession().update(tag);
        return tag;
    }

    /**
     * @see TagsDao#exist(Tags)
     */
    @Override
    public boolean exist(Tags tag) {
        return (Boolean) currentSession().createQuery("select exists(select 1 from Tags t where t.value = :vlu)")
                .setParameter("vlu", tag.getValue()).uniqueResult();
    }

    /**
     * @see TagsDao#delete(Integer)
     */
    @Override
    public void delete(Integer id) {
        Tags tags = (Tags) currentSession().get(Tags.class, id);
        if (tags != null) {
            currentSession().delete(tags);
        }
    }

    /**
     * @see TagsDao#deleteAll()
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Tags");
    }

    /**
     * @see TagsDao#getCount()
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Tags.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * @see TagsDao#findAll()
     */
    @Override
    public Collection<Tags> findAll() {
        return currentSession().createCriteria(Tags.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * @see TagsDao#findById(Integer)
     */
    @Override
    public Tags findById(Integer id) {
        return (Tags) currentSession().get(Tags.class, id);
    }

    /**
     * @see TagsDao#findByValue(String)
     */
    @Override
    public Tags findByValue(String value) {
        return (Tags) currentSession().createCriteria(Tags.class).add(Restrictions.eq("value", value))
                .uniqueResult();
    }
}
