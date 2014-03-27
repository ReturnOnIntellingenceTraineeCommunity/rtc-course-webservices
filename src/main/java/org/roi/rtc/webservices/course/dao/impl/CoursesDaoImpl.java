package org.roi.rtc.webservices.course.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.entities.CourseType;
import org.roi.rtc.webservices.course.entities.Courses;
import org.roi.rtc.webservices.course.model.CourseFilter;
import org.roi.rtc.webservices.course.model.Page;

import java.util.Collection;
import java.util.Date;

/**
 * Data Access Object Implementation
 * Extend by {@link AbstractDAO}
 *
 * @author Vladislav Pikus
 */
public class CoursesDaoImpl extends AbstractDAO<Courses> implements CoursesDao {

    public CoursesDaoImpl(SessionFactory factory) {
        super(factory);
    }

    /**
     * @see CoursesDao#create(Courses)
     */
    @Override
    public Courses create(Courses course) {
        currentSession().save(course);
        return course;
    }

    /**
     * @see CoursesDao#update(Courses)
     */
    @Override
    public Courses update(Courses course) {
        currentSession().update(course);
        return course;
    }

    /**
     * @see CoursesDao#exist(String)
     */
    @Override
    public boolean exist(String code) {
        return ((Long) currentSession().createQuery("select count(*) from Courses c where c.code = :code")
                .setParameter("code", code).uniqueResult()) != 0;
    }

    /**
     * @see CoursesDao#delete(Integer)
     */
    @Override
    public void delete(Integer id) {
        Courses courses = (Courses) currentSession().get(Courses.class, id);
        if (courses != null) {
            currentSession().delete(courses);
        }
    }

    /**
     * @see CoursesDao#deleteAll()
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Courses");
    }

    /**
     * @see CoursesDao#getCount()
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Courses.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * @see CoursesDao#findAll()
     */
    @Override
    public Collection<Courses> findAll() {
        return currentSession().createCriteria(Courses.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * @see CoursesDao#findById(Integer)
     */
    @Override
    public Courses findById(Integer id) {
        return (Courses) currentSession().get(Courses.class, id);
    }

    /**
     * @see CoursesDao#findByFilter(CourseFilter, Page)
     */
    @Override
    public Collection<Courses> findByFilter(CourseFilter filter, Page page) {
        Criteria criteria = currentSession().createCriteria(Courses.class);
        criteria.setFetchMode("tags", FetchMode.SELECT);
        criteria.setFetchMode("author", FetchMode.SELECT);
        final String title = filter.getTitle();
        if (title != null && !title.equals("")) {
            criteria.add(Restrictions.like("name", "%" + title + "%"));
        }
        final Date startDate = filter.getStartDate();
        if (startDate != null) {
            criteria.add(Restrictions.gt("startDate", startDate));
        }
        final Collection<String> categories = filter.getCategories();
        if (categories != null && categories.size() > 0) {
            final Disjunction catDis = Restrictions.disjunction();
            for (final String cat : categories) {
                catDis.add(Restrictions.eq("type", CourseType.valueOf(cat.toUpperCase())));
            }
            criteria.add(catDis);
        }
        final Collection<String> tags = filter.getTags();
        if (tags != null && tags.size() > 0) {
            criteria.createAlias("tags", "tags");
            final Disjunction tagDis = Restrictions.disjunction();
            for (final String tag : tags) {
                tagDis.add(Restrictions.eq("tags.value", tag));
            }
            criteria.add(tagDis);
        }
        return criteria.addOrder(Order.asc("id")).setFirstResult(page.getFirstResult())
                .setMaxResults(page.getMaxResult()).list();
    }

    /**
     * @see CoursesDao#findByCode(String)
     */
    @Override
    public Courses findByCode(String code) {
        return (Courses) currentSession().createCriteria(Courses.class)
                .add(Restrictions.eq("code", code)).uniqueResult();
    }

    /**
     * @see CoursesDao#delete(String)
     */
    @Override
    public void delete(String code) {
        Courses courses = findByCode(code);
        if (courses != null) {
            currentSession().delete(courses);
        }
    }

    /**
     * @see CoursesDao#merge(Courses)
     */
    @Override
    public Courses merge(Courses course) {
        currentSession().merge(course);
        return course;
    }
}
