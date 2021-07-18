package security.encryptor.keyprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@EnableConfigurationProperties(KeysConfiguration.class)
public class PropertiesFileSecretKeyProvider implements SecretKeyProvider{

    @Autowired
    private KeysConfiguration keys;

    @Override
    public SecretKey provide(String algorithm) {
        String encodedKey = keys.getKey(algorithm);
        byte[] decoded =  Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(decoded, 0, decoded.length, algorithm);
    }

}
