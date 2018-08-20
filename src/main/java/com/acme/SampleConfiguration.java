package com.acme;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

@Configuration
public class SampleConfiguration
        implements ApplicationContextInitializer<GenericApplicationContext> {

    @Bean
    public Foo foo() {
        return new Foo();
    }

    @Bean
    public Bar bar(Foo foo) {
        return new Bar(foo);
    }

    @Bean
    public CommandLineRunner runner(Bar bar) {
        return args -> {
            System.err.println("Bar: " + bar);
        };
    }

    @Override
    public void initialize(GenericApplicationContext context) {
        context.registerBean(SampleConfiguration.class);
        context.registerBean(Foo.class,
                () -> context.getBean(SampleConfiguration.class).foo());
        context.registerBean(Bar.class, () -> context.getBean(SampleConfiguration.class)
                .bar(context.getBean(Foo.class)));
        context.registerBean(CommandLineRunner.class, () -> context
                .getBean(SampleConfiguration.class).runner(context.getBean(Bar.class)));
    }
}
