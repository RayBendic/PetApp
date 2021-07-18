package aspect.exception.annotation;

import exception.AppException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public @interface WrapRuntimeException {

    Class<? extends RuntimeException>[] wrapped();
    Class<? extends AppException> wrapper();
    String wrapperMessage() default "";

}
