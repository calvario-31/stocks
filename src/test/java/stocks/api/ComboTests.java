package stocks.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stocks.parser.DataParser;

import java.io.File;
import java.io.IOException;

public class ComboTests extends BaseApiTests {

    private final String jsonPath = "src/test/resources/data/combinationJson.json";

    @Test
    public void deserializeJson() throws IOException {
        final var combination = objectMapper.readValue(new File(jsonPath), Object[][].class);
        System.out.println(combination[0][2]);
    }

    @Test
    public void jsonFileComboTest() throws IOException {
        final var combination = objectMapper.readValue(new File(jsonPath), Object[][].class);

        final var listData = DataParser.createDataList(combination);

        System.out.println(listData.size());
        System.out.println(listData.get(168).getListData());
        Assertions.assertFalse(listData.get(0).getListData().isEmpty());
    }

    @Test
    public void jsonApiComboTest() {
        final var listData = CombinationApi.getCombinations();

        System.out.println(listData.size());

        final var last = listData.get(listData.size() - 1);

        System.out.println(last);

        Assertions.assertFalse(last.getListData().isEmpty());
    }
}
