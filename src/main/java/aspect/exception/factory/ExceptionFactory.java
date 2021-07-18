package aspect.exception.factory;

import exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class ExceptionFactory {

    @Autowired
    private Map<Class<? extends AppException>, Function<String, ? extends AppException>> messageExceptions;
    @Autowired
    private Map<Class<? extends AppException>, BiFunction<String, Exception, ? extends AppException>> wrapperExceptions;


    public Supplier<? extends Throwable> getExceptionSupplier(Class<? extends AppException> exceptionClass, String message){
        return () -> messageExceptions.get(exceptionClass).apply(message);
    }

    public Exception getWrapper(Class<? extends AppException> wrapper, Exception wrapped, String message){
        return wrapperExceptions.get(wrapper).apply(message, wrapped);
    }

}
