package com.acme;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

public class SimpleApplication {

    public static void main(String[] args) throws Exception {
        GenericApplicationContext context = new GenericApplicationContext();
        @SuppressWarnings("unchecked")
        ApplicationContextInitializer<GenericApplicationContext> initializer = BeanUtils
                .instantiateClass(SampleConfiguration.class,
                        ApplicationContextInitializer.class);
        initializer.initialize(context);
        context.refresh();
    }
}
