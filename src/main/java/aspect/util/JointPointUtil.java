package aspect.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.javatuples.Pair;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JointPointUtil {

    public <T extends Annotation> T getAnnotationFromJointPoint(ProceedingJoinPoint joinPoint,
                                                                       Class<T> annotationClass){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        return method.getAnnotation(annotationClass);
    }


    public Map<String, Object> createParameterMap(ProceedingJoinPoint joinPoint){

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        Object[] args = joinPoint.getArgs();

        return parameterMap(parameters, args);
    }

    private Map<String, Object> parameterMap(Parameter[] parameters, Object[] args){

        return Stream.iterate(0, i -> i < parameters.length, i -> i + 1)
                .map(i -> new Pair<>(parameters[i].getName(), args[i]))
                .collect(Collectors.toMap(Pair::getValue0, Pair::getValue1));
    }

}
