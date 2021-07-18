package aspect.exception.factory;

import exception.AppException;
import exception.EncryptionException;
import exception.SqlAppException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;


@Configuration
public class ExceptionFactoryConfig {

    @Bean
    public Map<Class<? extends AppException>, Function<String, ? extends AppException>> messageExceptions(){

        Map<Class<? extends AppException>, Function<String, ? extends AppException>> map = new HashMap<>();

        map.put(AppException.class, AppException::new);
        map.put(SqlAppException.class, SqlAppException::new);
        map.put(EncryptionException.class, EncryptionException::new);

        return map;
    }

    @Bean
    public Map<Class<? extends AppException>, BiFunction<String, Throwable, ? extends AppException>> wrapperExceptions(){

        Map<Class<? extends AppException>, BiFunction<String, Throwable, ? extends AppException>> map = new HashMap<>();

        map.put(AppException.class, AppException::new);
        map.put(SqlAppException.class, SqlAppException::new);
        map.put(EncryptionException.class, EncryptionException::new);

        return map;
    }

}
