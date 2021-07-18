package security.encryptor;

public interface Encryptor{
    byte[] encrypt(byte[] source);
    byte[] decrypt(byte[] source);
}
