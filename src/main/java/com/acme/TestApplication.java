package com.acme;

import java.lang.reflect.Constructor;

import org.springframework.util.ReflectionUtils;

public class TestApplication {

    public static void main(String[] args) throws Exception {
        System.out.println(new TestApplication().getLocation());
    }

    private Object getLocation() throws Exception {
        String analyzerName = "org.springframework.boot.diagnostics.analyzer.ValidationExceptionFailureAnalyzer";
        try {
            Constructor<?> constructor = Class.forName(analyzerName)
                    .getDeclaredConstructor();
            ReflectionUtils.makeAccessible(constructor);
            return constructor.newInstance();
        }
        catch (Throwable ex) {
            System.out.println("Failed to load " + analyzerName + ", " + ex);
            return null;
        }
    }

}
