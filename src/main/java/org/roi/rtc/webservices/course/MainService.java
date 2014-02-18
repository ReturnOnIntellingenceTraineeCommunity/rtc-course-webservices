package org.roi.rtc.webservices.course;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.roi.rtc.webservices.course.config.MainServiceConfiguration;
import org.roi.rtc.webservices.course.resources.AuthorResource;
import org.roi.rtc.webservices.course.resources.HelloResource;
import org.roi.rtc.webservices.course.service.AuthorService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Vladislav Pikus
 */
public class MainService extends Service<MainServiceConfiguration> {
    private static ApplicationContext context;

    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext("spring-config.xml");
        new MainService().run(args);
    }

    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {
        bootstrap.setName("rtc-course-webservices");
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(MainServiceConfiguration conf, Environment env) throws Exception {
        //final AuthorDao authorDao = new AuthorDaoImpl(hibernate.getSessionFactory());
        env.addResource(new HelloResource(conf.getMessages()));
        env.addResource(new AuthorResource((AuthorService)context.getBean("authorService")));
    }
}
