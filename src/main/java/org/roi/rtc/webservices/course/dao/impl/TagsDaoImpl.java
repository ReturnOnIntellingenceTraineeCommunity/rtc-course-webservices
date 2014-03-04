package org.roi.rtc.webservices.course.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.roi.rtc.webservices.course.dao.TagsDao;
import org.roi.rtc.webservices.course.entities.Tags;

import java.util.Collection;

/**
 * Tag dao implementation
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
     * Save a tag in the DB
     *
     * @param tag
     * @return
     */
    @Override
    public Tags create(Tags tag) {
        currentSession().save(tag);
        return tag;
    }

    /**
     * Update a existing tag
     *
     * @param tag
     * @return
     */
    @Override
    public Tags update(Tags tag) {
        currentSession().update(tag);
        return tag;
    }

    /**
     * Check tagName exist
     *
     * @param tag
     * @return true if exist, false - not exist
     */
    @Override
    public boolean exist(Tags tag) {
        return (Boolean) currentSession().createQuery("select exists(select 1 from Tags t where t.value = :vlu)")
                .setParameter("vlu", tag.getValue()).uniqueResult();
    }

    /**
     * Delete a tag by id
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Tags tags = (Tags) currentSession().get(Tags.class, id);
        if (tags != null) {
            currentSession().delete(tags);
        }
    }

    /**
     * Delete all rows
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Tags");
    }

    /**
     * Get row count
     *
     * @return
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Tags.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * Find all tags
     *
     * @return
     */
    @Override
    public Collection<Tags> findAll() {
        return currentSession().createCriteria(Tags.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * Find a tag by id
     * @param id
     * @return
     */
    @Override
    public Tags findById(Integer id) {
        return (Tags) currentSession().get(Tags.class, id);
    }
}
