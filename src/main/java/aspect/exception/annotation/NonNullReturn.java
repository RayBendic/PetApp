package aspect.exception.annotation;

import exception.AppException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface NonNullReturn {

    Class<? extends AppException> exceptionOnNull() ;
    String message();
}
