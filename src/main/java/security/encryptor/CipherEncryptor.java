package security.encryptor;
import security.encryptor.cipher.CipherPair;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class CipherEncryptor implements Encryptor{

    private final CipherPair cipherPair;
    private final Encryptor nextEncryptor;

    public CipherEncryptor(CipherPair cipherPair, Encryptor nextEncryptor){
        this.cipherPair = cipherPair;
        this.nextEncryptor = nextEncryptor;
    }

    @Override
    public byte[] encrypt(byte[] source) {
        try {
            return nextEncryptor.encrypt(this.doEncrypt(source));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] source) {
        try{
            return this.doDecrypt(nextEncryptor.decrypt(source));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private byte[] doEncrypt(byte[] source) throws IllegalBlockSizeException, BadPaddingException {
        return cipherPair.getEncryptionCipher().doFinal(source);
    }

    private byte[] doDecrypt(byte[] source) throws IllegalBlockSizeException, BadPaddingException {
        return cipherPair.getDecryptionCipher().doFinal(source);
    }

}
