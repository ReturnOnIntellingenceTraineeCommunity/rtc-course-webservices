package org.roi.rtc.webservices.course;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import org.roi.rtc.webservices.course.config.MainServiceConfiguration;
import org.roi.rtc.webservices.course.resources.HelloResource;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 14.02.14
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class MainService extends Service<MainServiceConfiguration> {
    public static void main(String[] args) throws Exception {
        new MainService().run(args);
    }

    @Override
    public void initialize(Bootstrap<MainServiceConfiguration> bootstrap) {
        bootstrap.setName("dropwizard-example");
    }

    @Override
    public void run(MainServiceConfiguration conf, Environment env) throws Exception {
        env.addResource(new HelloResource(conf.getMessages()));
    }
}
