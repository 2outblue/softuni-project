package com.ngfrt.appmain.config;

import com.ngfrt.appmain.interceptor.ExecutionTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    private ExecutionTimeInterceptor executionTimeInterceptor;

    @Autowired
    public InterceptorConfig(ExecutionTimeInterceptor executionTimeInterceptor) {
        this.executionTimeInterceptor = executionTimeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(executionTimeInterceptor)
                .addPathPatterns("/**"); // Intercept all requests
    }
}
