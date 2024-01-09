package stocks.utilities;

import stocks.model.Credential;

import java.util.Properties;

public class TestData {
    public static Credential getCredentials(Properties properties) {
        return new Credential(properties.getProperty("username"), properties.getProperty("password"));
    }
}
