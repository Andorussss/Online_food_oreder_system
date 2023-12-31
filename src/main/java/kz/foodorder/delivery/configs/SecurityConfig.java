package kz.foodorder.delivery.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration

public class SecurityConfig implements WebMvcConfigurer {

    public void addViewController(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");
    }

}
