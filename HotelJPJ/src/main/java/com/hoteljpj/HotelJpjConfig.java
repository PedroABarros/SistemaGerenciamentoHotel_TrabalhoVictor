package com.hoteljpj;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



// SE DER ERRO ME FALA
// ATT: JACKSON
@Configuration
public class HotelJpjConfig implements  WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS");

    }
}


