package org.roi.rtc.webservices.course.dao;

import org.roi.rtc.webservices.course.entities.Tags;

import java.util.Collection;

/**
 * @author Vladislav Pikus
 */
public interface TagsDao {
    Tags create(Tags tag);

    Tags update(Tags tag);

    boolean exist(Tags tag);

    void delete(Integer id);

    void deleteAll();

    Integer getCount();

    Collection<Tags> findAll();

    Tags findById(Integer id);
}
