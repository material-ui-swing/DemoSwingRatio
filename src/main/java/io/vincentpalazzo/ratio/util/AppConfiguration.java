package io.vincentpalazzo.ratio.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @author https://github.com/vincenzopalazzo
 */
public class AppConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);

    private static AppConfiguration SINGLETON;

    private Properties properties;

    private AppConfiguration(){ initProperties(); }

    private void initProperties(){
        properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream(Constant.NAME_FILE_PROPERTIS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AppConfiguration getInstance(){
        if (SINGLETON == null){
            SINGLETON = new AppConfiguration();
        }
        return SINGLETON;
    }

    public String getValueWith(String nameProprietis) {
        if(nameProprietis == null){
            LOGGER.error("The name properties is null");
            throw new IllegalArgumentException("The name properties is null");
        }
        return properties.getProperty(nameProprietis);
    }
}
