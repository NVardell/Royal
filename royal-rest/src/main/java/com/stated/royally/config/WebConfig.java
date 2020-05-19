package com.stated.royally.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * WebMVC Configuration
 *
 * @author Nate Vardell
 * @since 2/28/2020
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource private InterceptorConfig interceptor;

    /**
     * Add custom logging interceptor to the app registry
     * @param registry Registered Interceptors
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
