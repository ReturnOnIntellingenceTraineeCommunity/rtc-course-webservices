package org.roi.rtc.webservices.course;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import org.roi.rtc.webservices.course.config.MainServiceConfiguration;
import org.roi.rtc.webservices.course.dao.AuthorDao;
import org.roi.rtc.webservices.course.dao.CoursesDao;
import org.roi.rtc.webservices.course.dao.TagsDao;
import org.roi.rtc.webservices.course.dao.impl.AuthorDaoImpl;
import org.roi.rtc.webservices.course.dao.impl.CoursesDaoImpl;
import org.roi.rtc.webservices.course.dao.impl.TagsDaoImpl;
import org.roi.rtc.webservices.course.entities.Author;
import org.roi.rtc.webservices.course.entities.Courses;
import org.roi.rtc.webservices.course.entities.Tags;
import org.roi.rtc.webservices.course.resources.*;

/**
 * @author Vladislav Pikus
 */
public class MainService extends Service<MainServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    private final HibernateBundle<MainServiceConfiguration> hibernate =
            new HibernateBundle<MainServiceConfiguration>(Author.class, Courses.class,
                    Tags.class) {
        @Override
        public DatabaseConfiguration getDatabaseConfiguration(MainServiceConfiguration configuration) {
            return configuration.getDatabase();
        }
    };

    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {
        bootstrap.setName("rtc-course-webservices");
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(MainServiceConfiguration conf, Environment env) throws Exception {
        final AuthorDao authorDao = new AuthorDaoImpl(hibernate.getSessionFactory());
        env.addResource(new AuthorResource(authorDao));

        final TagsDao tagsDao = new TagsDaoImpl(hibernate.getSessionFactory());
        env.addResource(new TagsResource(tagsDao));

        final CoursesDao coursesDao = new CoursesDaoImpl(hibernate.getSessionFactory());
        env.addResource(new CoursesResource(coursesDao));

        env.addResource(new HelloResource(conf.getMessages()));
        env.addResource(new CourseTypeResource());
    }
}
