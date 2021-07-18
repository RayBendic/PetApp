package aspect.exception;


import aspect.exception.annotation.WrapRuntimeException;
import aspect.exception.factory.ExceptionFactory;
import aspect.util.JointPointUtil;
import aspect.util.MessagePlaceholderResolver;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

@Aspect
@Component
public class WrapperAspect {

    @Autowired
    private ExceptionFactory exceptionFactory;
    @Autowired
    private MessagePlaceholderResolver messagePlaceholderResolver;
    @Autowired
    private JointPointUtil jointPointUtil;

    @Around("@annotation(WrapRuntimeException)")
    public Object wrap(ProceedingJoinPoint joinPoint, WrapRuntimeException annotation) throws Throwable {

        try {
            return joinPoint.proceed();
        }
        catch (Exception exception) {
            throw wrapException(joinPoint, annotation, exception);
        }
    }

    private Exception wrapException(ProceedingJoinPoint joinPoint, WrapRuntimeException annotation, Exception throwable){

        Supplier<String> messageSupplier = () -> messagePlaceholderResolver
                .resolve(annotation.wrapperMessage(), jointPointUtil.createParameterMap(joinPoint));

        return matchException(annotation, throwable, messageSupplier)
                .orElse(throwable);
    }

    private Optional<Exception> matchException(WrapRuntimeException annotation, Exception throwable, Supplier<String> messageSupplier){
        return Arrays.stream(annotation.wrapped())
                .filter(wrappedExceptionClass -> wrappedExceptionClass.equals(throwable.getClass()))
                .findFirst()
                .map(wrappedExceptionClass -> getWrapper(annotation, throwable, messageSupplier.get()));
    }

    private Exception getWrapper(WrapRuntimeException annotation, Exception throwable, String resolvedMessage){
        return exceptionFactory.getWrapper(annotation.wrapper(), throwable, resolvedMessage);
    }

    private WrapRuntimeException annotation(ProceedingJoinPoint joinPoint){
        return jointPointUtil.getAnnotationFromJointPoint(joinPoint, WrapRuntimeException.class);
    }


}
