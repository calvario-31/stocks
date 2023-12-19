package stocks.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static List<String> leerFile(File file) {
        final var lista = new ArrayList<String>(); //lista del contenido del file

        try {
            final var scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                lista.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            Logs.error("FileNotFoundException: %s", fileNotFoundException.getLocalizedMessage());
        }

        return lista;
    }

    public static void escribirFile(File file, List<String> lista) {
        try {
            final var fileWriter = new FileWriter(file);

            for (var linea : lista) {
                final var lineaEscribir = String.format("%s%n", linea); //escribo la línea y un salto de línea
                fileWriter.write(lineaEscribir);
            }

            fileWriter.close();
        } catch (IOException ioException) {
            Logs.error("IOException: %s", ioException.getLocalizedMessage());
        }
    }
}
