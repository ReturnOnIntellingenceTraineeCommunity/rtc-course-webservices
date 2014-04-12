package net.github.rtc.micro.course.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import net.github.rtc.micro.course.entities.Tag;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import net.github.rtc.micro.course.dao.TagDao;

import java.util.Collection;

/**
 * Data Access Object Implementation
 * Extend by {@link AbstractDAO}
 *
 * @author Vladislav Pikus
 */
public class TagDaoImpl extends AbstractDAO<Tag> implements TagDao {
    /**
     * Constructor
     *
     * @param factory session factory
     */
    public TagDaoImpl(SessionFactory factory) {
        super(factory);
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#create(net.github.rtc.micro.course.entities.Tag)
     */
    @Override
    public Tag create(Tag tag) {
        currentSession().save(tag);
        return tag;
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#update(net.github.rtc.micro.course.entities.Tag)
     */
    @Override
    public Tag update(Tag tag) {
        currentSession().update(tag);
        return tag;
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#exist(net.github.rtc.micro.course.entities.Tag)
     */
    @Override
    public boolean exist(Tag tag) {
        return (Boolean) currentSession().createQuery("select exists(select 1 from Tag t where t.value = :vlu)")
                .setParameter("vlu", tag.getValue()).uniqueResult();
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#delete(Integer)
     */
    @Override
    public void delete(Integer id) {
        Tag tag = (Tag) currentSession().get(Tag.class, id);
        if (tag != null) {
            currentSession().delete(tag);
        }
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#deleteAll()
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Tag");
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#getCount()
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Tag.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#findAll()
     */
    @Override
    public Collection<Tag> findAll() {
        return currentSession().createCriteria(Tag.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#get(Integer)
     */
    @Override
    public Tag get(Integer id) {
        return (Tag) currentSession().get(Tag.class, id);
    }

    /**
     * @see net.github.rtc.micro.course.dao.TagDao#findByValue(String)
     */
    @Override
    public Tag findByValue(String value) {
        return (Tag) currentSession().createCriteria(Tag.class).add(Restrictions.eq("value", value))
                .uniqueResult();
    }
}
