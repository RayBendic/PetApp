package security.encryptor;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import security.encryptor.keyprovider.KeysConfiguration;

import java.util.Map;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties(KeysConfiguration.class)
@SpringBootTest(classes = KeysConfiguration.class)
@ActiveProfiles("test")
public class KeysConfigurationTest {

    @Autowired
    private KeysConfiguration keysConfiguration;

    @Test
    public void shouldReadKeysFromYml(){
        Map<String, String> keys = (Map<String, String>) ReflectionTestUtils.getField(keysConfiguration, "keys");
        Assert.assertNotNull(keys);
        Assert.assertEquals(3, keys.size());
    }

}
