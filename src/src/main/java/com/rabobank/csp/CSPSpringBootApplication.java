package com.rabobank.csp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author Laxman Jadi
 * 
 * This is main class.
 *
 */

@SpringBootApplication
public class CSPSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(CSPSpringBootApplication.class, args);
    }
    
    @Bean
    public WebMvcConfigurer crosConfigurer() {
    	return new WebMvcConfigurerAdapter() {
    		public void addCorsMappings(CorsRegistry registry) {
    			registry.addMapping("/*").allowedOrigins("*");
    		}
		};
    }
}
