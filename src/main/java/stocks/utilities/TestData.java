package stocks.utilities;

import stocks.model.Credential;
import stocks.model.Data;
import stocks.model.SingleData;

import java.util.ArrayList;
import java.util.List;

public class TestData {
    public static List<Data> getAllData() {
        final var listData = new ArrayList<Data>();

        listData.add(getData());
        listData.add(getData2());
        listData.add(getData3());

        return listData;
    }

    private static Data getData() {
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

    private static Data getData2() {
        final var listData = new ArrayList<SingleData>();

        listData.add(new SingleData("all"));
        listData.add(new SingleData(2018));
        listData.add(new SingleData(4));
        listData.add(new SingleData(5));
        listData.add(new SingleData(2045));
        listData.add(new SingleData(12));
        listData.add(new SingleData(22));
        listData.add(new SingleData("hl2"));
        listData.add(new SingleData("Ehma"));
        listData.add(new SingleData(45));
        listData.add(new SingleData(true));
        listData.add(new SingleData(true));
        listData.add(new SingleData(true));
        listData.add(new SingleData(6));
        listData.add(new SingleData(52));

        return new Data(listData);
    }

    private static Data getData3() {
        final var listData = new ArrayList<SingleData>();

        listData.add(new SingleData("long"));
        listData.add(new SingleData(2018));
        listData.add(new SingleData(4));
        listData.add(new SingleData(5));
        listData.add(new SingleData(2035));
        listData.add(new SingleData(11));
        listData.add(new SingleData(21));
        listData.add(new SingleData("close"));
        listData.add(new SingleData("Hma"));
        listData.add(new SingleData(40));
        listData.add(new SingleData(true));
        listData.add(new SingleData(false));
        listData.add(new SingleData(true));
        listData.add(new SingleData(6));
        listData.add(new SingleData(42));

        return new Data(listData);
    }

    public static Credential getCredentials() {
        return new Credential("pruebas_aaaa@proton.me", "aBcDeFg123456_");
    }

    public static Credential getCredentials2() {
        return new Credential("pruebas_bbbb@proton.me", "aBcDeFg123456_");
    }
}
