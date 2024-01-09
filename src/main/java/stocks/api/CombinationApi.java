package stocks.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import stocks.model.Data;
import stocks.parser.DataParser;
import stocks.utilities.ApiUtils;
import stocks.utilities.Logs;

import java.io.IOException;
import java.util.List;

public class CombinationApi extends BaseApi {
    private static final String resourcePath = "combinaciones";
    private static final OkHttpClient client = new OkHttpClient();
    protected static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Data> getCombinations() {
        final var url = ApiUtils.generateUrl(baseUrl, resourcePath);

        final var request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            final var response = client.newCall(request).execute();
            if (response.body() != null) {
                final var objectMatrix = objectMapper.readValue(
                        response.body().string(),
                        Object[][].class
                );
                return DataParser.createDataList(objectMatrix);
            } else {
                throw new RuntimeException("Empty Response body");
            }
        } catch (IOException ioException) {
            Logs.error("Error calling get combo api");
            throw new RuntimeException("Error calling get combo api: " + ioException.getLocalizedMessage());
        }
    }
}