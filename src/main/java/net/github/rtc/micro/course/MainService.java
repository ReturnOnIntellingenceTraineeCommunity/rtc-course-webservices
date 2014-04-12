package net.github.rtc.micro.course;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.hibernate.SessionFactoryHealthCheck;
import net.github.rtc.micro.course.dao.CourseDao;
import net.github.rtc.micro.course.dao.impl.TagDaoImpl;
import net.github.rtc.micro.course.entities.Tag;
import org.hibernate.SessionFactory;
import net.github.rtc.micro.course.config.MainServiceConfiguration;
import net.github.rtc.micro.course.dao.AuthorDao;
import net.github.rtc.micro.course.dao.TagDao;
import net.github.rtc.micro.course.dao.impl.AuthorDaoImpl;
import net.github.rtc.micro.course.dao.impl.CourseDaoImpl;
import net.github.rtc.micro.course.entities.Author;
import net.github.rtc.micro.course.entities.Course;
import net.github.rtc.micro.course.resource.*;

/**
 * @author Vladislav Pikus
 */
public class MainService extends Service<MainServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    private final HibernateBundle<MainServiceConfiguration> hibernate =
            new HibernateBundle<MainServiceConfiguration>(Author.class, Course.class,
                    Tag.class) {
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
        final SessionFactory sessionFactory = hibernate.getSessionFactory();
        env.addHealthCheck(new SessionFactoryHealthCheck("mySql", sessionFactory, conf.getDatabase().getValidationQuery()));
        final AuthorDao authorDao = new AuthorDaoImpl(sessionFactory);
        env.addResource(new AuthorResource(authorDao));

        final TagDao tagDao = new TagDaoImpl(hibernate.getSessionFactory());
        env.addResource(new TagResource(tagDao));

        final CourseDao courseDao = new CourseDaoImpl(hibernate.getSessionFactory());
        env.addResource(new CourseResource(courseDao, authorDao, tagDao));

        env.addResource(new CourseTypeResource());
    }
}
