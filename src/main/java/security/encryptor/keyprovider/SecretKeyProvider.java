package security.encryptor.keyprovider;

import javax.crypto.SecretKey;

public interface SecretKeyProvider {
    SecretKey provide(String algorithm);
}
