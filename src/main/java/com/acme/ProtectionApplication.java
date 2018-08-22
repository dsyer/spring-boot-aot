package com.acme;

import java.security.CodeSource;
import java.security.ProtectionDomain;

import org.apache.commons.logging.Log;

public class ProtectionApplication {

    public static void main(String[] args) throws Exception {
        System.out.println(new ProtectionApplication().getLocation(Log.class));
    }
    private Object getLocation(Class<?> type) {
        try {
            ProtectionDomain protectionDomain = type.getProtectionDomain();
            CodeSource codeSource = protectionDomain.getCodeSource();
            if (codeSource != null) {
                return codeSource.getLocation();
            }
        }
        catch (SecurityException ex) {
            // Unable to determine location
        }
        return "unknown location";
    }

}
