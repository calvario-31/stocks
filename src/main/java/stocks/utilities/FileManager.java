package stocks.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

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
}
