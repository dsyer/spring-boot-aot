package com.acme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.BackgroundPreinitializer;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;

public class BackgroundApplication {

    public static void main(String[] args) throws Exception {
        BackgroundPreinitializer preinit = new BackgroundPreinitializer();
        SpringApplication application = new SpringApplication(BackgroundApplication.class);
        ApplicationStartingEvent starting = new ApplicationStartingEvent(application,
                args);
        preinit.onApplicationEvent(starting);
        ApplicationFailedEvent failed = new ApplicationFailedEvent(application, args,
                null, new RuntimeException());
        preinit.onApplicationEvent(failed);
    }
}
