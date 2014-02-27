package org.roi.rtc.webservices.course.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 14.02.14
 * Time: 22:16
 * To change this template use File | Settings | File Templates.
 */
public class MainServiceConfiguration extends Configuration {

    @Valid
    private MessagesConfiguration messages;

    public MessagesConfiguration getMessages() {
        return messages;
    }

    public void setMessages(MessagesConfiguration messages) {
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
