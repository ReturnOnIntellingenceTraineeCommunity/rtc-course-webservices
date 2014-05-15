package net.github.rtc.micro.course.jobs;

import com.yammer.dropwizard.lifecycle.Managed;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

/**
 * Created by Chernichenko Bogdan on 15.05.2014.
 */
public class QuartzManager implements Managed {

    private Scheduler scheduler;
//    private QuartzSchedulerMonitor schedulerMonitor;
    private QuartzJobMonitor jobMonitor;

    public  QuartzManager(SchedulerFactory sf) throws SchedulerException {
        scheduler = sf.getScheduler();
//        schedulerMonitor = new QuartzSchedulerMonitor(); // Implements SchedulerListener
        scheduler.getListenerManager();//.addSchedulerListener(schedulerMonitor);
        jobMonitor = new QuartzJobMonitor(); // Implements JobListener
    }

    @Override
    public void start() throws Exception {
        scheduler.start();
        // Make our Job listener cover all scheduled jobs
        scheduler.getListenerManager().addJobListener(jobMonitor, EverythingMatcher.allJobs());
    }

    @Override
    public void stop() throws Exception {
        scheduler.getListenerManager().removeJobListener(jobMonitor.getName());
        scheduler.shutdown(true);
    }

    public boolean isHealthy(){
        return true;
    }

    /*
      A rudimentary example to get the state of the jobs and scheduler.
    */
    public String getState() {
        return "";
        //return  " Jobs:" + jobMonitor.getState();
    }

}
