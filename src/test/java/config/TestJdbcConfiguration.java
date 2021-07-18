package config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@TestConfiguration
public class TestJdbcConfiguration{

    private static final String PASSWORD_DB_SET_UP_SCRIPT = "classpath:sql-scripts/password/set-up.sql";
    private static final String PASSWORD_DB_POPULATION_SCRIPT = "classpath:sql-scripts/password/population.sql";

    @Bean
    public DataSource h2PasswordDatasource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(PASSWORD_DB_SET_UP_SCRIPT)
                .addScript(PASSWORD_DB_POPULATION_SCRIPT)
                .build();
    }

    @Bean
    public NamedParameterJdbcTemplate jdbcTemplate(){
        return new NamedParameterJdbcTemplate(h2PasswordDatasource());
    }

}
