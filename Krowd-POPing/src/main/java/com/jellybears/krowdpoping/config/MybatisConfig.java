package com.jellybears.krowdpoping.config;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.jellybears.krowdpoping", annotationClass = Mapper.class)
public class MybatisConfig {
}
