package com.study.kubernete.user.autoconfig;


import com.study.kubernete.user.core.RequestHeaderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestHeaderConfig {
    @Bean
    public RequestHeaderServiceImpl createRequestHeaderService() {
        return new RequestHeaderServiceImpl();
    }
}
