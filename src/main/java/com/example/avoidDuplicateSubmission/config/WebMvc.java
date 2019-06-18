package com.example.avoidDuplicateSubmission.config;

import com.example.avoidDuplicateSubmission.Interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvc implements WebMvcConfigurer {
    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor());
    }
}
