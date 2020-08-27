package utils;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Log4j2
public class PropertyManager {

    private String propertyFilePath;
    private Properties prop;

    public PropertyManager() {
        propertyFilePath = System.getProperty("user.dir") + "/src/test/resources/test.properties";
        loadData();
    }

    private void loadData() {
        prop = new Properties();
        try {
            prop.load(new FileInputStream(propertyFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get(String propertyName) {
        return prop.getProperty(propertyName);
    }

    public List<String> getList(String propertyName) {
        String property = prop.getProperty(propertyName);
        if (StringUtils.isEmpty(property)) {
            log.error(String.format("Property: '%s' doesn't exist or empty", propertyName));
        }
        return Arrays.asList(prop.getProperty(propertyName).split("\\s*,\\s*"));
    }
}