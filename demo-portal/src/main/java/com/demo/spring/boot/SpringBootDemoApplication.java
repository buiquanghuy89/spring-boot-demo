package com.demo.spring.boot;

import com.demo.spring.boot.config.AppConfig;
import com.demo.spring.boot.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@SpringBootApplication
@PropertySource(value = {"classpath:application-${spring.profiles.active}.properties"})
@EnableCaching
public class SpringBootDemoApplication extends SpringBootServletInitializer {
    private Logger log = LoggerFactory.getLogger(SpringBootDemoApplication.class);
    @Value("${random}")
    private int version;
    @Value("${pa_data_path}")
    private String pa_data_path;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootDemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("version: {}", version);
        if (StringUtils.validString(pa_data_path)) {
            AppConfig.pa_data_path = pa_data_path;
        }
    }
}
