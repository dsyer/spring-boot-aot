package com.acme;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

@Configuration
public class SampleConfiguration
        implements ApplicationContextInitializer<GenericApplicationContext> {
    
    private static Log logger = LogFactory.getLog(SampleConfiguration.class);
    
    public SampleConfiguration() {
        logger.info("Creating Initializer");
    }

    @Bean
    public Foo foo() {
        logger.info("Creating");
        return new Foo();
    }

    @Bean
    public Bar bar(Foo foo) {
        logger.info("Creating: " + foo);
        return new Bar(foo);
    }

    @Bean
    public CommandLineRunner runner(Bar bar) {
        logger.info("Creating: " + bar);
        return args -> {
            logger.info("Bar: " + bar);
        };
    }

    @Override
    public void initialize(GenericApplicationContext context) {
        logger.info("Initializing");
        context.registerBean(SampleConfiguration.class);
        context.registerBean(Foo.class,
                () -> context.getBean(SampleConfiguration.class).foo());
        context.registerBean(Bar.class, () -> context.getBean(SampleConfiguration.class)
                .bar(context.getBean(Foo.class)));
        context.registerBean(CommandLineRunner.class, () -> context
                .getBean(SampleConfiguration.class).runner(context.getBean(Bar.class)));
    }
}
