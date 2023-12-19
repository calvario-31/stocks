package stocks.utilities;

import stocks.model.Credential;
import stocks.model.Data;
import stocks.parsear.CsvParser;

import java.io.File;
import java.util.List;

public class TestData {
    public static List<Data> getAllData(String path) {
        final var file = new File(path);
        final var listRawInput = FileManager.leerFile(file);
        Logs.debug("raw list: %s", listRawInput);

        return CsvParser.createDataList(listRawInput);
    }

    public static Credential getCredentials() {
        return new Credential("pruebas_aaaa@proton.me", "aBcDeFg123456_");
    }

    public static Credential getCredentials2() {
        return new Credential("pruebas_bbbb@proton.me", "aBcDeFg123456_");
    }
}
