package com.geovis.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * <p>
 * 启动类
 * </p>
 *
 * @author 曾德实
 * @since 2022-09-23
 */
@SpringBootApplication
public class GeovisManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeovisManagerApplication.class, args);
    }
}
