package org.roi.rtc.webservices.course.config;

import com.yammer.dropwizard.config.Configuration;

import javax.validation.Valid;

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
}
