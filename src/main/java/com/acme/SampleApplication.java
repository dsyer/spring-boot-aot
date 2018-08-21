package com.acme;

import org.springframework.context.support.GenericApplicationContext;

public class SampleApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(
                new Class<?>[] { SampleApplication.class });
        app.setApplicationContextClass(GenericApplicationContext.class);
        // app.addInitializers(new SampleConfiguration());
        app.setRegisterShutdownHook(false);
        app.run(args);
    }
}
