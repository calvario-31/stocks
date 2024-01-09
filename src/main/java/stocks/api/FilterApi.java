package stocks.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import stocks.model.Data;
import stocks.model.ResultFilter;
import stocks.utilities.ApiUtils;
import stocks.utilities.Logs;

import java.io.IOException;

public class FilterApi extends BaseApi {
    private static final String resourcePath = "filtrosCombi";
    private static final OkHttpClient client = new OkHttpClient();
    protected static final ObjectMapper objectMapper = new ObjectMapper();

    public static ResultFilter getFilters(Data data) throws JsonProcessingException {
        final var url = ApiUtils.generateUrl(baseUrl, resourcePath);

        final var body = RequestBody.create(
                objectMapper.writeValueAsString(data),
                MediaType.parse("application/json")
        );

        final var request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try {
            final var response = client.newCall(request).execute();
            if (response.body() != null) {
                return objectMapper.readValue(
                        response.body().string(),
                        ResultFilter.class
                );
            } else {
                throw new RuntimeException("Empty Response body");
            }
        } catch (IOException ioException) {
            Logs.error("Error calling get combo api");
            throw new RuntimeException("Error calling get combo api: " + ioException.getLocalizedMessage());
        }
    }
}
