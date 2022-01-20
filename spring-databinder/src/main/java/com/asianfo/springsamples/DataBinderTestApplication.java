package com.asianfo.springsamples;

import com.asianfo.springsamples.conversionService.UserConversionService;
import com.asianfo.springsamples.converter.UserTypeConverter;
import com.asianfo.springsamples.entity.Role;
import com.asianfo.springsamples.entity.User;
import com.asianfo.springsamples.propertyEditor.UserPropertyEditor;
import com.asianfo.springsamples.validator.*;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.*;


/**
 * Hello world!
 */
@SpringBootApplication
public class DataBinderTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataBinderTestApplication.class, args);
    }
    @Bean
    public TomcatConnectorCustomizer tomcatConnectorCustomizer(){
        return connector -> {
            connector.setPort(12380);
        };
    }
    @Bean
    public WebMvcConfigurer WebMvcConfigurer() {
        return new WebMvcConfigurer() {
//            @Override
//            public Validator getValidator() {
//                Map<Class, MyValidator> map = new HashMap<>();
//                map.put(User.class,new UserValidator());
//                map.put(Role.class,new RoleValidator());
//                SpringValidator springValidator = new SpringValidator(map);
//                return springValidator;
//            }
        };
    }
}
