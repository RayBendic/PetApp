package exception;

public class SqlAppException extends AppException{

    public SqlAppException(String message) {
        super(message);
    }

    public SqlAppException(String message, Throwable t) {
        super(message, t);
    }
}
