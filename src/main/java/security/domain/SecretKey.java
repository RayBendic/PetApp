package security.domain;

public class SecretKey {

    private final String publicKey;
    private final String password;

    public SecretKey(String publicKey, String password) {
        this.publicKey = publicKey;
        this.password = password;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPassphrase() {
        return password;
    }
}
