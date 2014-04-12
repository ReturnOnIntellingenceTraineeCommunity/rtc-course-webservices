package net.github.rtc.micro.course.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
}
