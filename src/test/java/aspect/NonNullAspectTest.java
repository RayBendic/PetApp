package aspect;

import aspect.exception.NonNullAspect;
import aspect.exception.annotation.NonNullReturn;
import aspect.exception.factory.ExceptionFactory;
import aspect.util.JointPointUtil;
import aspect.util.MessagePlaceholderResolver;
import exception.AppException;
import exception.SqlAppException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.function.Supplier;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

@ContextConfiguration(classes = {NonNullAspect.class})
@RunWith(SpringRunner.class)
public class NonNullAspectTest {

    @Autowired
    private NonNullAspect nonNullAspect;
    @Mock
    private ProceedingJoinPoint joinPointMock;
    @Mock
    private NonNullReturn annotationMock;
    @MockBean
    private ExceptionFactory exceptionFactoryMock;
    @MockBean
    private MessagePlaceholderResolver messageResolverMock;
    @MockBean
    private JointPointUtil jointPointUtilMock;


    @Test
    public void shouldReturnIfNotNull() throws Throwable {

        when(joinPointMock.proceed()).thenReturn(new Object());
        when(annotationMock.message()).thenReturn("Message");

        doReturn(SqlAppException.class)
                .when(annotationMock)
                .exceptionOnNull();

        Assert.assertNotNull(nonNullAspect.throwRuntimeOnNull(joinPointMock, annotationMock));
    }

    @Test(expected = SqlAppException.class)
    public void shouldThrowIfNull() throws Throwable {

        when(joinPointMock.proceed())
                .thenReturn(null);

        Class<? extends AppException> exceptionClass = SqlAppException.class;
        doReturn(exceptionClass)
                .when(annotationMock)
                .exceptionOnNull();

        Supplier<Exception> supplier = () -> new SqlAppException("");
        doReturn(supplier)
                .when(exceptionFactoryMock)
                .getExceptionSupplier(any(), any());

        nonNullAspect.throwRuntimeOnNull(joinPointMock, annotationMock);
    }


    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfJointPointException() throws Throwable {
        when(joinPointMock.proceed()).thenThrow(new IllegalStateException());
        nonNullAspect.throwRuntimeOnNull(joinPointMock, annotationMock);
    }

    @After
    public void resetMock(){
        reset(joinPointMock);
        reset(annotationMock);
        reset(exceptionFactoryMock);
        reset(messageResolverMock);
        reset(jointPointUtilMock);
    }
}
