package net.github.rtc.micro.course.config;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Vlablack
 * Date: 14.02.14
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */
public class MessageConfiguration {
    @NotNull
    private String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
