package net.github.rtc.micro.course.jobs;

/**
 * Created by Chernichenko Bogdan on 15.05.2014.
 */
import com.yammer.metrics.core.HealthCheck;
import org.quartz.SchedulerException;

public class QuartzSchedulerHealthCheck extends HealthCheck{

    private QuartzManager quartzManager;

    public QuartzSchedulerHealthCheck(QuartzManager qm)  throws SchedulerException {
        super("Quartz Scheduler Health Check");
        this.quartzManager = qm;
    }

    /**
     * Checks the state of the Quartz Scheduler and all scheduled Quartz jobs
     * @return Result Healthy if the scheduler and all of it's jobs are running withing acceptable parameters
     * @throws Exception if unable to check the state of the scheduler or its jobs
     */
    @Override
    protected Result check() throws Exception {
        // The Quartz is currently in an error state
        if (!quartzManager.isHealthy())
            return Result.unhealthy(quartzManager.getState());
        else
            return Result.healthy();
    }

}
