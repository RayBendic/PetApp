package security.encryptor;

public class ChainFinalizer implements Encryptor{

    @Override
    public byte[] encrypt(byte[] source) {
        return source;
    }

    @Override
    public byte[] decrypt(byte[] source) {
        return source;
    }
}
