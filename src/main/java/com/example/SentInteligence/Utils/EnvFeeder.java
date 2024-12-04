package com.example.SentInteligence.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;

import static com.example.SentInteligence.CommonConstants.CommonConstants.*;

public final class EnvFeeder {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnvFeeder.class);

    private static final String SERVICE_PROPERTIES_PATH = "SERVICE_PROPERTIES_PATH";
    private static final String SERVICE_PROPERTIES_PATH_DEFAULT = Objects.requireNonNull(EnvFeeder.class.getClassLoader().getResource("devus1-config.properties")).getPath();
    private static final String MAIN_PROPERTIES_PATH_DEFAULT = Objects.requireNonNull(EnvFeeder.class.getClassLoader().getResource("config.properties")).getPath();
    private static final Properties MAIN_PROPERTIES = loadMainProperties();
    private static final Properties PROPERTIES = loadProperties();

    private EnvFeeder() {}

    public static String get(String propertyName, String defaultValue) {
        String val = get(propertyName);
        return (val == null) ? defaultValue : val;
    }

    public static String get(String propertyName, String defaultValue, Object... args) {
        return get(String.format(propertyName, args), defaultValue);
    }

    public static int getInt(String propertyName) {
        String val = get(propertyName);
        return Integer.parseInt(val);
    }

    public static boolean getBoolean(String propertyName) {
        String val = get(propertyName);
        return Boolean.parseBoolean(val);
    }

    public static String get(String propertyName) {
        return (null != getEnv(propertyName)) ? getEnv(propertyName)
                : PROPERTIES.getProperty(propertyName);
    }

    public static void set(String propertyName, String propertyValue) {
        PROPERTIES.setProperty(propertyName, propertyValue);
    }

    public static void clear(String propertyName) {
        PROPERTIES.remove(propertyName);
    }

    public static String getMainProp(String propertyName) {
        return (null != getEnv(propertyName)) ? getEnv(propertyName)
                : MAIN_PROPERTIES.getProperty(propertyName);
    }

    private static String getEnv(String variable) {
        return ((Function<String, String>) System::getenv).apply(variable);
    }

    private static Properties loadProperties() {
        String propertiesFile = propertiesFilePath();
        LOGGER.info("Loading properties from : {}", propertiesFile);
        Properties prop = new Properties();
        try(InputStream propStream = EnvFeeder.class.getClassLoader().getResourceAsStream(getConfigFileName())) {
            prop.load(propStream);
            LOGGER.info("property being loaded : {} ", prop);
            return prop;
        } catch (IOException e) {
            LOGGER.error("IO exception occurred while loading a property file : {} ", prop);
            return new Properties();
        }
    }

    private static Properties loadMainProperties() {
        String propertiesFile = mainPropertiesFilePath();
        LOGGER.info("Loading properties from : {}", propertiesFile);
        Properties prop = new Properties();
        try(InputStream propStream = EnvFeeder.class.getClassLoader().getResourceAsStream("config.properties")) {
            prop.load(propStream);
            LOGGER.info("property being loaded : {} ", prop);
            return prop;
        } catch (IOException e) {
            LOGGER.error("IO exception occurred while loading a property file : {} ", prop);
            return new Properties();
        }
    }

    private static String propertiesFilePath() {
        if (getEnv(SERVICE_PROPERTIES_PATH) == null) {
            return Paths.get(SERVICE_PROPERTIES_PATH_DEFAULT).toString();
        }
        return Paths.get(getEnv(SERVICE_PROPERTIES_PATH)).toString();
    }

    private static String mainPropertiesFilePath() {
        if (getEnv(SERVICE_PROPERTIES_PATH) == null) {
            return Paths.get(MAIN_PROPERTIES_PATH_DEFAULT).toString();
        }
        return Paths.get(getEnv(SERVICE_PROPERTIES_PATH)).toString();
    }

    private static String getConfigFileName(){
        String envName = MAIN_PROPERTIES.getProperty(TARGET_ENV);
        String fileName;
        switch (envName) {
            case DEVUS1:
                fileName = "devus1-config.properties";
                break;

            case INTGUS1:
                fileName = "intgus1-config.properties";
                break;

            case QAUS1:
                fileName = "qaus1-config.properties";
                break;

            case APPSTAGING:
                fileName = "appstaging-config.properties";
                break;

            case LOADUS1:
                fileName = "loadus1-config.properties";
                break;

            case PRODUS1:
                fileName = "produs1-config.properties";
                break;
            case PRODSG1:
                fileName = "prodsg1-config.properties";
                break;

            default:
                throw new IllegalArgumentException("Error occurred while getting filename, Illegal env selected");
        }

        return  fileName;
    }

    public static void reLoadProps() {
        PROPERTIES.putAll(loadProperties());
    }

}
