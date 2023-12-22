package stocks.utilities;

import stocks.model.Credential;
import stocks.model.Data;
import stocks.parsear.DataParser;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class TestData {
    public static List<Data> getAllData(String path) {
        final var file = new File(path);
        final var listRawInput = FileManager.readFile(file);
        Logs.debug("raw list: %s", listRawInput);

        return DataParser.createDataList(listRawInput);
    }

    public static Credential getCredentials(Properties properties) {
        return new Credential(properties.getProperty("username"), properties.getProperty("password"));
    }
}
