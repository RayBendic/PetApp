package aspect.exception;

import aspect.exception.annotation.NonNullReturn;
import aspect.exception.factory.ExceptionFactory;
import aspect.util.JointPointUtil;
import aspect.util.MessagePlaceholderResolver;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class NonNullAspect {

    @Autowired
    private ExceptionFactory exceptionFactory;
    @Autowired
    private MessagePlaceholderResolver messageResolver;
    @Autowired
    private JointPointUtil jointPointUtil;

    @Around("@annotation(NonNullReturn)")
    public Object throwRuntimeOnNull(ProceedingJoinPoint joinPoint, NonNullReturn annotation) throws Throwable {

        Object returnedObject = joinPoint.proceed();

        if (returnedObject == null){
            throw exceptionFactory
                    .getExceptionSupplier(annotation.exceptionOnNull(), resolveAnnotationMessage(joinPoint, annotation))
                    .get();
        }
        return returnedObject;
    }


    private String resolveAnnotationMessage(ProceedingJoinPoint joinPoint, NonNullReturn annotation){
        Map<String, Object> methodParameters = jointPointUtil.createParameterMap(joinPoint);

        NonNullAspect n = new NonNullAspect(){
            @Override
            public Object throwRuntimeOnNull(ProceedingJoinPoint joinPoint, NonNullReturn annotation) throws Throwable{
                return null;
            }
        };

        return messageResolver.resolve(annotation.message(), methodParameters);
    }


}
