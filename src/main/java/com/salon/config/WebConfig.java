package com.salon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/customer").setViewName("client");
        registry.addViewController("/comments").setViewName("comments");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/manager").setViewName("manager");
        registry.addViewController("/price-list").setViewName("pricelist");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/service").setViewName("services");
        registry.addViewController("/trends").setViewName("trends");
        registry.addViewController("/employee").setViewName("worker");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/game").setViewName("calc");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/img/**",
                              "/css/**",
                              "/js/**",
                              "/import/**")
                .addResourceLocations(
                        "classpath:/css/",
                        "classpath:/img/",
                        "classpath:/js/",
                        "classpath:/import/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
