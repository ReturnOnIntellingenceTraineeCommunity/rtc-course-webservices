package net.github.rtc.micro.course.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Configuration
 *
 * @author Vladislav Pikus
 */
public class MainServiceConfiguration extends Configuration {

    @Valid
    private MessageConfiguration messages;

    public MessageConfiguration getMessages() {
        return messages;
    }

    public void setMessages(MessageConfiguration messages) {
        this.messages = messages;
    }

    @Valid
    @NotNull
    @JsonProperty
    private DatabaseConfiguration database = new DatabaseConfiguration();

    public DatabaseConfiguration getDatabase() {
        return database;
    }

    @Valid
    @JsonProperty
    private Map<String,String> quartzSettings = new HashMap<String, String>();

    public Properties getSchedulerFactoryProperties(){
        Properties sfProps = new Properties();
        // Fixed Quartz settings. They could easily be added to the YAML config file
        sfProps.setProperty("org.quartz.plugin.jobInitializer.class", "org.quartz.plugins.xml.XMLSchedulingDataProcessorPlugin");
        sfProps.setProperty("org.quartz.plugin.jobInitializer.failOnFileNotFound", "true");
        // Job XML files can be changed without restarting the service by being check at regular intervals
        sfProps.setProperty("org.quartz.plugin.jobInitializer.scanInterval", "0");
        sfProps.setProperty("org.quartz.plugin.jobInitializer.wrapInUserTransaction", "false");
        // Quartz checks for updates. This should be turned off for production systems.
        sfProps.setProperty("org.quartz.scheduler.skipUpdateCheck","true");

        // Quartz settings configured in YML file
        sfProps.setProperty("org.quartz.scheduler.instanceName", quartzSettings.get("instanceName"));
        sfProps.setProperty("org.quartz.threadPool.class", quartzSettings.get("threadPoolClass"));
        sfProps.setProperty("org.quartz.threadPool.threadCount", quartzSettings.get("threadCount"));
        sfProps.setProperty("org.quartz.threadPool.threadPriority", quartzSettings.get("threadPriority"));
        sfProps.setProperty("org.quartz.jobStore.class", quartzSettings.get("jobStoreClass"));
        sfProps.setProperty("org.quartz.plugin.jobInitializer.fileNames", quartzSettings.get("jobFiles"));

        return sfProps;
    }
}
