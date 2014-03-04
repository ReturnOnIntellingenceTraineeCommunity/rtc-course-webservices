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
 * Course dao implementation
 *
 * @author Vladislav Pikus
 */
public class CoursesDaoImpl extends AbstractDAO<Courses> implements CoursesDao {
    /**
     * Constructor
     *
     * @param factory session factory
     */
    public CoursesDaoImpl(SessionFactory factory) {
        super(factory);
    }

    /**
     * Save a new course in the DB
     *
     * @param course
     * @return
     */
    @Override
    public Courses create(Courses course) {
        currentSession().save(course);
        return course;
    }

    /**
     * Update an existing course
     *
     * @param course
     * @return
     */
    @Override
    public Courses update(Courses course) {
        currentSession().update(course);
        return course;
    }

    /**
     * Check code exist
     *
     * @param code code
     * @return true if exist, false - not exist
     */
    @Override
    public boolean exist(String code) {
        return ((Long) currentSession().createQuery("select count(*) from Courses c where c.code = :code")
                .setParameter("code", code).uniqueResult()) != 0;
    }

    /**
     * Delete an existing course by id
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        Courses courses = (Courses) currentSession().get(Courses.class, id);
        if (courses != null) {
            currentSession().delete(courses);
        }
    }

    /**
     * Delete all rows
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Courses");
    }

    /**
     * Get row count
     *
     * @return
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Courses.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * Find all courses in the DB
     *
     * @return
     */
    @Override
    public Collection<Courses> findAll() {
        return currentSession().createCriteria(Courses.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * Find a course by id
     *
     * @param id
     * @return
     */
    @Override
    public Courses findById(Integer id) {
        return (Courses) currentSession().get(Courses.class, id);
    }
}
