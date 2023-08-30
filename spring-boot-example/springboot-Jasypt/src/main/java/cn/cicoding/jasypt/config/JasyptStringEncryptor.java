package cn.cicoding.jasypt.config;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/**
 * @author Jackieonway
 * @version \$Id: JasyptStringEncryptor.java, v 0.1 2019-07-30 9:48 Jackieonway Exp $$
 */
public class JasyptStringEncryptor implements StringEncryptor {

    private static final Logger log = LoggerFactory.getLogger(JasyptStringEncryptor.class);

    private final StandardPBEByteEncryptor byteEncryptor;
    private final Base64 base64;

    public JasyptStringEncryptor() {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("EdLKOREFDMI/sddnc@A");
        config.setAlgorithm("PBEWithMD5AndDES");
        this.byteEncryptor = new StandardPBEByteEncryptor();
        this.byteEncryptor.setConfig(config);
        this.base64 = new Base64();
    }

    public JasyptStringEncryptor(Environment environment) {
        byteEncryptor = new StandardPBEByteEncryptor();
        byteEncryptor.setConfig(getConfig(environment));
        this.base64 = new Base64();
    }

    public JasyptStringEncryptor(String password) {
        SimplePBEConfig config = new SimplePBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword(password);
        byteEncryptor = new StandardPBEByteEncryptor();
        byteEncryptor.setConfig(config);
        this.base64 = new Base64();
    }

    public JasyptStringEncryptor(SimpleStringPBEConfig config) {
        byteEncryptor = new StandardPBEByteEncryptor();
        byteEncryptor.setConfig(config);
        this.base64 = new Base64();
    }

    @Override
    public String encrypt(String s) {
        byte[] encrypt = byteEncryptor.encrypt((s).getBytes());
        byte[] encode = base64.encode(encrypt);
        return new String(encode,StandardCharsets.UTF_8);
    }

    @Override
    public String decrypt(String s) {
        byte[] decode = base64.decode(s.getBytes());
        byte[] decrypt  = byteEncryptor.decrypt(decode);
        return new String(decrypt,StandardCharsets.UTF_8);
    }
    private PBEConfig getConfig(Environment e){
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(getRequiredProperty(e, "jasypt.encryptor.password"));
        config.setAlgorithm(getProperty(e, "jasypt.encryptor.algorithm", "PBEWithMD5AndDES"));
        config.setKeyObtentionIterations(getProperty(e, "jasypt.encryptor.keyObtentionIterations", "1000"));
        config.setPoolSize(getProperty(e, "jasypt.encryptor.poolSize", "1"));
        config.setProviderName(getProperty(e, "jasypt.encryptor.providerName", null));
        config.setSaltGeneratorClassName(getProperty(e, "jasypt.encryptor.saltGeneratorClassname", "org.jasypt.salt.RandomSaltGenerator"));
        config.setStringOutputType(getProperty(e, "jasypt.encryptor.stringOutputType", "base64"));
        return config;
    }

    private static String getProperty(Environment environment, String key, String defaultValue) {
        if (!propertyExists(environment, key)) {
            log.info("Encryptor config not found for property {}, using default value: {}", key, defaultValue);
        }
        return environment.getProperty(key, defaultValue);
    }

    private static boolean propertyExists(Environment environment, String key) {
        return environment.getProperty(key) != null;
    }

    private static String getRequiredProperty(Environment environment, String key) {
        if (!propertyExists(environment, key)) {
            throw new IllegalStateException(String.format("Required Encryption configuration property missing: %s", key));
        }
        return environment.getProperty(key);
    }

}