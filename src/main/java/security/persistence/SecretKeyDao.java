package security.persistence;

import security.domain.SecretKey;

public interface SecretKeyDao {

    SecretKey getSecretKey(String publicKey);
    void storeSecretKey(SecretKey secretKey);
    void updateSecretKey(String publicKey, String newPassphrase);
    void deleteSecretKey(String publicKey);

}
