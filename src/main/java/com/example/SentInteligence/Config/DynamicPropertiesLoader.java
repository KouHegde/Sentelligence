package com.example.SentInteligence.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



@Configuration
@PropertySource("classpath:application.properties")
public class DynamicPropertiesLoader {

    @Bean(name = "applicationProperties")
    public Map<String, String> applicationProperties() throws IOException {
        // Load the properties file
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));

        // Convert Properties to a Map
        Map<String, String> propertiesMap = new HashMap<>();
        for (String key : properties.stringPropertyNames()) {
            propertiesMap.put(key, properties.getProperty(key));
        }
        return propertiesMap;
    }
}