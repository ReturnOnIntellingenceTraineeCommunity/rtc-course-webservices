package org.roi.rtc.webservices.course.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * All possible type of courses
 *
 * @author Vladislav Pikus
 */
@XmlRootElement
public enum CourseType {
    QA, DEV, BA
}
