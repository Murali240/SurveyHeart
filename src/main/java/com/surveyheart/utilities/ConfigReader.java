package com.surveyheart.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/** Utility class to read values from config.properties */
 public class ConfigReader {
    private static Properties properties;

    /** Stores all key-value pairs from config.properties */
    static {
        try {
            String path = System.getProperty("user.dir") + "/resources/config.properties";
            FileInputStream fis = new FileInputStream(path);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file", e);
        }
    }
    

    /** Returns the property value for the given key */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

 
    
    
    
    
 
 
 }
 






