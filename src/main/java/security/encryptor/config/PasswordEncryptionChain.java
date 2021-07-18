package security.encryptor.config;

import org.springframework.context.annotation.Bean;
import security.encryptor.CipherEncryptor;
import security.encryptor.Encryptor;
import security.encryptor.ChainFinalizer;
import security.encryptor.cipher.CipherPair;
import security.encryptor.cipher.CipherPairFactory;
import security.encryptor.keyprovider.SecretKeyProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.crypto.SecretKey;

@Configuration
@PropertySource("classpath:encryption.properties")
public class PasswordEncryptionChain {

    @Value("${encryption.algorithm.des.name}")
    private String DES;
    @Value("${encryption.algorithm.des.name}")
    private String AES;
    @Value("${encryption.algorithm.des.name}")
    private String RC4;

    @Value("${encryption.algorithm.des.config}")
    private String DES_CONFIG;
    @Value("${encryption.algorithm.des.config}")
    private String AES_CONFIG;
    @Value("${encryption.algorithm.des.config}")
    private String RC4_CONFIG;

    @Autowired
    private SecretKeyProvider provider;
    @Autowired
    private CipherPairFactory cipherPairFactory;

    @Bean
    public Encryptor encryptionChain() {
        return new CipherEncryptor(DES_CIPHER(),
                new CipherEncryptor(AES_CIPHER(),
                        new CipherEncryptor(RC4_CIPHER(),
                                new ChainFinalizer()))
        );
    }

    private CipherPair DES_CIPHER(){
        return cipherPair(provider.provide(DES), DES_CONFIG);
    }

    private CipherPair AES_CIPHER(){
        return cipherPair(provider.provide(AES), AES_CONFIG);
    }

    private CipherPair RC4_CIPHER(){
        return cipherPair(provider.provide(RC4), RC4_CONFIG);
    }


    private CipherPair cipherPair(SecretKey secretKey, String config) {
        return cipherPairFactory.getCipherPair(secretKey, config);
    }


}
