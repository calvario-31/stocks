package stocks.utilities;

import okhttp3.HttpUrl;
import stocks.Main;
import stocks.model.Credential;

import java.util.Objects;

public class ApiUtils {
    public static String generateUrl(String baseUrl, String resourcePath) {
        Logs.debug("generateUrl");
        final var rawUrl = String.format("%s/%s", baseUrl, resourcePath);
        Logs.debug("raw url: %s", rawUrl);

        final var properties = FileManager.readProperties(Main.propertiesFilePath);
        final var credentials = new Credential(
                properties.getProperty("api-username"),
                properties.getProperty("api-password")
        );

        final var url = Objects.requireNonNull(HttpUrl.parse(rawUrl)).newBuilder()
                .addQueryParameter("user", credentials.username())
                .addQueryParameter("password", credentials.password())
                .build().toString();

        Logs.debug("url to string: %s", url);
        return url;
    }
}
