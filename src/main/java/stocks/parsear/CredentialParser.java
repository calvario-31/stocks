package stocks.parsear;

import stocks.model.Credential;

import java.util.List;

public class CredentialParser {
    public static Credential createCredentials(List<String> credentialList) {
        final var username = credentialList.get(0);
        final var password = credentialList.get(1);

        return new Credential(username, password);
    }
}
