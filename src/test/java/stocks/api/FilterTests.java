package stocks.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import stocks.model.Data;

import java.io.File;
import java.io.IOException;

public class FilterTests extends BaseApiTests {
    private final String postResultJsonPath = "src/test/resources/data/postResult.json";

    @Test
    public void postBodyTest() throws IOException {
        final var data = objectMapper.readValue(new File(postResultJsonPath), Data.class);
        final var filters = FilterApi.getFilters(data);
        System.out.println(filters);
        Assertions.assertFalse(filters.avgBarsInLosingTradesFilter().isEmpty());
    }
}
