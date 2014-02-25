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
 * @author Vladislav Pikus
 */
public class TagsDaoImpl extends AbstractDAO<Tags> implements TagsDao {
    public TagsDaoImpl(SessionFactory factory) {
        super(factory);
    }

    @Override
    public Tags create(Tags tag) {
        currentSession().save(tag);
        return tag;
    }

    @Override
    public Tags update(Tags tag) {
        currentSession().update(tag);
        return tag;
    }

    @Override
    public boolean exist(Tags tag) {
        return (Boolean) currentSession().createQuery("select exists(select 1 from Tags t where t.value = :vlu)")
                .setParameter("vlu", tag.getValue()).uniqueResult();
    }

    @Override
    public void delete(Integer id) {
        Tags tags = (Tags) currentSession().get(Tags.class, id);
        if (tags != null) {
            currentSession().delete(tags);
        }
    }

    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Tags");
    }

    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Tags.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    @Override
    public Collection<Tags> findAll() {
        return currentSession().createCriteria(Tags.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    @Override
    public Tags findById(Integer id) {
        return (Tags) currentSession().get(Tags.class, id);
    }
}
