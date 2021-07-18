package security.encryptor;

import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import security.encryptor.keyprovider.KeysConfiguration;
import security.encryptor.keyprovider.PropertiesFileSecretKeyProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PropertiesFileSecretKeyProvider.class})
public class SecretKeyProviderTest {

    @MockBean
    private KeysConfiguration keysConfiguration;

    @Autowired
    private PropertiesFileSecretKeyProvider provider;

    @Test
    public void shouldProvideSecretKey(){
        Mockito.when(keysConfiguration.getKey(any())).thenReturn("key");
        Assert.assertNotNull(provider.provide("DES"));
    }
}
