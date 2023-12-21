package com.jellybears.krowdpoping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static/");

        // 이미지 불러올 수 있도록 세팅
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///c:/jellybears-springboot/upload/");

        registry.addResourceHandler("/thumbPath/**")
                .addResourceLocations("file:///c:/jellybears-springboot/img/upload/thumbnail/");
        registry.addResourceHandler("/imagePath/**")
                .addResourceLocations("file:///c:/jellybears-springboot/img/upload/original/");

    }
}
