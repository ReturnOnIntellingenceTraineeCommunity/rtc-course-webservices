package net.github.rtc.micro.course.dao.impl;

import com.yammer.dropwizard.hibernate.AbstractDAO;
import net.github.rtc.micro.course.dao.CourseDao;
import net.github.rtc.micro.course.entities.Course;
import net.github.rtc.micro.course.model.dto.PageDto;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import net.github.rtc.micro.course.entities.CourseType;
import net.github.rtc.micro.course.entities.Status;
import net.github.rtc.micro.course.model.dto.CourseSearchCriteriaDto;

import java.util.Collection;
import java.util.Date;

/**
 * Data Access Object Implementation
 * Extend by {@link AbstractDAO}
 *
 * @author Vladislav Pikus
 */
public class CourseDaoImpl extends AbstractDAO<Course> implements CourseDao {

    public CourseDaoImpl(SessionFactory factory) {
        super(factory);
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#create(net.github.rtc.micro.course.entities.Course)
     */
    @Override
    public Course create(Course course) {
        currentSession().save(course);
        return course;
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#update(net.github.rtc.micro.course.entities.Course)
     */
    @Override
    public Course update(Course course) {
        currentSession().update(course);
        return course;
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#exist(String)
     */
    @Override
    public boolean exist(String code) {
        return ((Long) currentSession().createQuery("select count(*) from Course c where c.code = :code")
                .setParameter("code", code).uniqueResult()) != 0;
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#delete(Integer)
     */
    @Override
    public void delete(Integer id) {
        Course course = (Course) currentSession().get(Course.class, id);
        if (course != null) {
            currentSession().delete(course);
        }
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#deleteAll()
     */
    @Override
    public void deleteAll() {
        currentSession().createQuery("delete from Course");
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#getCount()
     */
    @Override
    public Integer getCount() {
        return ((Long) currentSession().createCriteria(Course.class).setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#findAll()
     */
    @Override
    public Collection<Course> findAll() {
        return currentSession().createCriteria(Course.class).addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#findById(Integer)
     */
    @Override
    public Course findById(Integer id) {
        return (Course) currentSession().get(Course.class, id);
    }

    /**
     * Build search criteria by filter
     * @param filter filter course search
     * @return criteria
     */
    private Criteria buildCriteria(CourseSearchCriteriaDto filter) {
        Criteria criteria = currentSession().createCriteria(Course.class);
        criteria.setFetchMode("tags", FetchMode.SELECT);
        criteria.setFetchMode("author", FetchMode.SELECT);
        final String title = filter.getTitle();
        if (title != null && !title.equals("")) {
            criteria.add(Restrictions.like("name", "%" + title + "%"));
        }
        final String status = filter.getStatus();
        if (status != null && !status.equals("")) {
            final Disjunction stat = Restrictions.disjunction();
            stat.add(Restrictions.eq("status", Status.valueOf(status.toUpperCase())));
            criteria.add(stat);
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

        return criteria;
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#findByCriteria(net.github.rtc.micro.course.model.dto.CourseSearchCriteriaDto, net.github.rtc.micro.course.model.dto.PageDto)
     */
    @Override
    public Collection<Course> findByCriteria(CourseSearchCriteriaDto filter, PageDto pageDto) {
        return buildCriteria(filter).addOrder(Order.asc("id")).setFirstResult(pageDto.getFirstResult())
                .setMaxResults(pageDto.getMaxResult()).list();
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#getCount(net.github.rtc.micro.course.model.dto.CourseSearchCriteriaDto)
     */
    @Override
    public Integer getCount(CourseSearchCriteriaDto filter) {
        return ((Long)buildCriteria(filter).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#findByCode(String)
     */
    @Override
    public Course findByCode(String code) {
        return (Course) currentSession().createCriteria(Course.class)
                .add(Restrictions.eq("code", code)).uniqueResult();
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#delete(String)
     */
    @Override
    public void delete(String code) {
        Course course = findByCode(code);
        if (course != null) {
            currentSession().delete(course);
        }
    }

    /**
     * @see net.github.rtc.micro.course.dao.CourseDao#merge(net.github.rtc.micro.course.entities.Course)
     */
    @Override
    public Course merge(Course course) {
        currentSession().merge(course);
        return course;
    }
}
