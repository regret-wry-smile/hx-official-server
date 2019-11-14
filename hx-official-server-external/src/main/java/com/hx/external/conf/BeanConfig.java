package com.hx.external.conf;

import com.hx.external.domain.SMS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class BeanConfig {

    @Bean
    public Map<String, SMS> phoneNumMap(){
        return new ConcurrentHashMap<>();
    }

}
