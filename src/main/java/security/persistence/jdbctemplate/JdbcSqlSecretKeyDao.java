package security.persistence.jdbctemplate;

import aspect.exception.annotation.NonNullReturn;
import aspect.exception.annotation.WrapRuntimeException;
import exception.AppException;
import exception.SqlAppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import security.domain.SecretKey;
import security.encryptor.Encryptor;
import security.persistence.SecretKeyDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcSqlSecretKeyDao implements SecretKeyDao {

    private static final String GET_BY_PK = "SELECT Public_Key, Passphrase FROM Private_Key WHERE Public_Key = :publicKey";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    @NonNullReturn(
            exceptionOnNull = SqlAppException.class,
            message = "HA! Public key: ${publicKey} return null"
    )
    @WrapRuntimeException(
            wrapped = {SqlAppException.class, IndexOutOfBoundsException.class},
            wrapper = AppException.class,
            wrapperMessage = "Failed for publicKey ${publicKey}"
    )
    public SecretKey getSecretKey(String publicKey) {

        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("publicKey", publicKey);

        return jdbcTemplate.queryForObject(GET_BY_PK, parameters, new SecretKeyMapper());
    }

    @Override
    public void storeSecretKey(SecretKey secretKey) {



    }

    @Override
    public void updateSecretKey(String publicKey, String newPassphrase) {

    }

    @Override
    public void deleteSecretKey(String publicKey) {

    }


    private static final class SecretKeyMapper implements RowMapper<SecretKey>{

        @Override
        public SecretKey mapRow(ResultSet resultSet, int i) throws SQLException {
            return new SecretKey(resultSet.getString("Public_Key"), resultSet.getString("Passphrase"));
        }

    }

    public <T, R extends SecretKeyDao & Encryptor> void test (T rt, R r){ }

}

