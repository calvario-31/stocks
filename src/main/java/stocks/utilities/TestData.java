package stocks.utilities;

import stocks.model.Credential;
import stocks.model.Data;
import stocks.model.SingleData;

import java.util.ArrayList;

public class TestData {
    public static Data getData() {
        final var listData = new ArrayList<SingleData>();

        listData.add(new SingleData("short"));
        listData.add(new SingleData(2016));
        listData.add(new SingleData(2));
        listData.add(new SingleData(3));
        listData.add(new SingleData(2040));
        listData.add(new SingleData(11));
        listData.add(new SingleData(25));
        listData.add(new SingleData("low"));
        listData.add(new SingleData("Thma"));
        listData.add(new SingleData(45));
        listData.add(new SingleData(false));
        listData.add(new SingleData(true));
        listData.add(new SingleData(false));
        listData.add(new SingleData(5));
        listData.add(new SingleData(50));

        return new Data(listData);
    }

    public static Credential getCredentials() {
        return new Credential("pruebas_aaaa@proton.me", "aBcDeFg123456_");
    }

    public static Credential getCredentials2() {
        return new Credential("pruebas_bbbb@proton.me", "aBcDeFg123456_");
    }
}
