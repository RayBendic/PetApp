package security.encryptor.cipher;

import javax.crypto.Cipher;

public class CipherPair {

    private final Cipher encryptionCipher;
    private final Cipher decryptionCipher;

    public CipherPair(Cipher encryptionCipher, Cipher decryptionCipher) {
        this.encryptionCipher = encryptionCipher;
        this.decryptionCipher = decryptionCipher;
    }

    public Cipher getEncryptionCipher() {
        return encryptionCipher;
    }

    public Cipher getDecryptionCipher() {
        return decryptionCipher;
    }
}
