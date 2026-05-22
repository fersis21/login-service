package com.kaiser.login.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author Fernando Apaza
 *
 */

@SpringBootApplication
//@ComponentScan(basePackages = {"com.kaiser"})
public class loginServiceApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(loginServiceApplication.class,args);
    }
}