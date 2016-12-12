package ru.dz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

//@Configuration
//@EnableWebMvc
//@ComponentScan("ru.dz")
public class WebAppConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/resources/**").addResourceLocations("/static/resources/");
//    }
//
//
//    @Bean
//    public FreeMarkerViewResolver setupViewResolver() {
//        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
//        resolver.setPrefix("");
//        resolver.setSuffix(".ftl");
//        resolver.setCache(true);
//        resolver.setContentType("text/html; charset=UTF-8");
//        resolver.setViewClass(FreeMarkerView.class);
//
//        return resolver;
//    }
//
//    @Bean
//    public FreeMarkerConfig freeMarkerConfig(){
//        FreeMarkerConfigurer freeMarkerConfig = new FreeMarkerConfigurer();
//        freeMarkerConfig.setTemplateLoaderPath("/templates/");
//        freeMarkerConfig.setDefaultEncoding("UTF-8");
//        return freeMarkerConfig;
//    }

}
