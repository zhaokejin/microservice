package cn.cicoding.jasypt.config;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
* @author Jackieonway
* @version \$Id: JasyptConfiguration.java, v 0.1 2019-07-30 9:22 Jackieonway Exp $$
*/
@Configuration
public class JasyptConfiguration {

   @Bean("jasyptStringEncryptor")
   public StringEncryptor stringEncryptor(Environment environment){
       return new JasyptStringEncryptor(environment);
   }
}