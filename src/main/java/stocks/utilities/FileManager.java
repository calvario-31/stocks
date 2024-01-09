package stocks.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class FileManager {
    public static void redirectStdErr() {
        Logs.debug("Redirecting stderr");
        final var file = new File("src/test/resources/logs/stderr.log");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final var ps = new PrintStream(fos);
        System.setErr(ps);
    }

    public static Properties readProperties(String path) {
        final var properties = new Properties();

        try {
            properties.load(new FileInputStream(path));
        } catch (IOException ioException) {
            Logs.error("Cannot read properties, IOException: %s", ioException.getLocalizedMessage());
        }

        return properties;
    }
}
