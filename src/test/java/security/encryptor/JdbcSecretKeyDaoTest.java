package security.encryptor;

import config.TestJdbcConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import security.domain.SecretKey;
import security.persistence.jdbctemplate.JdbcSqlSecretKeyDao;

@RunWith(SpringRunner.class)
@Import(TestJdbcConfiguration.class)
@SpringBootTest(classes = JdbcSqlSecretKeyDao.class)
public class JdbcSecretKeyDaoTest {


    @Autowired
    private JdbcSqlSecretKeyDao jdbcSqlPasswordDao;

    @Test
    public void shouldRetrieveValidPassword(){

        String publicKey = "A";
        String expectedPublicKey = "A";
        String expectedPassphrase = "a";

        SecretKey output = jdbcSqlPasswordDao.getSecretKey(publicKey);
        Assert.assertEquals(expectedPublicKey, output.getPublicKey());
        Assert.assertEquals(expectedPassphrase, output.getPassphrase());
    }

}
