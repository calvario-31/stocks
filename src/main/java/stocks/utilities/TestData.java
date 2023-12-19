package stocks.utilities;

import stocks.model.Credential;
import stocks.model.Data;
import stocks.parsear.CredentialParser;
import stocks.parsear.DataParser;

import java.io.File;
import java.util.List;

public class TestData {
    public static List<Data> getAllData(String path) {
        final var file = new File(path);
        final var listRawInput = FileManager.readFile(file);
        Logs.debug("raw list: %s", listRawInput);

        return DataParser.createDataList(listRawInput);
    }

    public static Credential getCredentials(String path) {
        final var file = new File(path);
        final var listRawInput = FileManager.readFile(file);
        return CredentialParser.createCredentials(listRawInput);
    }

    public static Credential getCredentials2() {
        return new Credential("pruebas_bbbb@proton.me", "aBcDeFg123456_");
    }
}
