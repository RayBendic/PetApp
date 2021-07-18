package security.encryptor.cipher;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class CipherPairFactory {

    public CipherPair getCipherPair(SecretKey secretKey, String cipherConfig){
        return new CipherPair(
                cipher(Cipher.ENCRYPT_MODE, secretKey, cipherConfig),
                cipher(Cipher.DECRYPT_MODE, secretKey, cipherConfig)
        );
    }

    private Cipher cipher(int mode, SecretKey secretKey, String cipherConfig) {
        try {
            return initCipher(mode, secretKey, cipherConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Cipher initCipher(int mode, SecretKey secretKey, String cipherConfig) throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException {

        Cipher cipher = Cipher.getInstance(cipherConfig);
        cipher.init(mode, secretKey);
        return cipher;
    }




}
