package aspect;

import aspect.exception.factory.ExceptionFactory;
import aspect.exception.factory.ExceptionFactoryConfig;
import exception.AppException;
import exception.EncryptionException;
import exception.SqlAppException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ExceptionFactory.class, ExceptionFactoryConfig.class})
public class ExceptionFactoryTest {

    @Autowired
    private ExceptionFactory exceptionFactory;

    List<Class<? extends AppException>> exceptions = List.of(
            AppException.class,
            SqlAppException.class,
            EncryptionException.class
    );

    @Test
    public void shouldContainAllMessageExceptions(){
        boolean allMatch = privateMap("messageExceptions").keySet().containsAll(exceptions);
        Assert.assertTrue(allMatch);
    }

    @Test
    public void shouldContainAllWrapperExceptions(){
        boolean allMatch = privateMap("messageExceptions").keySet().containsAll(exceptions);
        Assert.assertTrue(allMatch);
    }


    private Map<Class<? extends AppException>, ?> privateMap(String mapName){
        return (Map<Class<? extends AppException>, ?>) ReflectionTestUtils.getField(exceptionFactory, mapName);
    }
}
