package exception;

public class EncryptionException extends AppException{

    public EncryptionException(String message) {
        super(message);
    }

    public EncryptionException(String message, Throwable t){
        super(message, t);
    }

}
