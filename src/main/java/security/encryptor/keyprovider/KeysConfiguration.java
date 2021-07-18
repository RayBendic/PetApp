package security.encryptor.keyprovider;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

@ConfigurationProperties(prefix = "secrets")
public class KeysConfiguration {

    private Map<String, String> keys;

    public KeysConfiguration(Map<String, String> keys) {
        this.keys = keys;
    }

    public String getKey(String algorithm){
        return keys.get(algorithm);
    }

    public Map<String, String> getKeys(){
        return keys;
    }

    public void setKeys(Map<String, String> keys) {
        this.keys = keys;
    }
}
