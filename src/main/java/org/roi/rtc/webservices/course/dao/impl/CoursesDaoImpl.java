package org.roi.rtc.webservices.course.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.entities.Courses;

import java.util.Collection;

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
     * @see CoursesDao
     */
    @Override
    public Courses create(Courses course) {
        currentSession().save(course);
        return course;
    }

    /**
     * @see CoursesDao
     */
    @Override
    public Courses update(Courses course) {
        currentSession().update(course);
        return course;
    }

    /**
     * @see CoursesDao
     */
    @Override
    public boolean exist(String code) {
        return ((Long) currentSession().createQuery("select count(*) from Courses c where c.code = :code")
                .setParameter("code", code).uniqueResult()) != 0;
    }

    /**
     * @see CoursesDao
     */
    @Override
    public void delete(Integer id) {
        Courses courses = (Courses) currentSession().get(Courses.class, id);
        if (courses != null) {
            currentSession().delete(courses);
        }
    }

    /**
     * @see CoursesDao
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Courses");
    }

    /**
     * @see CoursesDao
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Courses.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * @see CoursesDao
     */
    @Override
    public Collection<Courses> findAll() {
        return currentSession().createCriteria(Courses.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * @see CoursesDao
     */
    @Override
    public Courses findById(Integer id) {
        return (Courses) currentSession().get(Courses.class, id);
    }
}
