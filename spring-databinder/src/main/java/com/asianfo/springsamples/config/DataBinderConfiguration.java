package com.asianfo.springsamples.config;

import com.asianfo.out.config.OutConfiguration;
import com.asianfo.springsamples.entity.OldUser;
import com.asianfo.springsamples.entity.User;
import com.asianfo.springsamples.propertyEditor.DatePropertyEditor;
import com.asianfo.springsamples.propertyEditor.UserPropertyEditor;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Import(OutConfiguration.class)
@EnableConfigurationProperties(OldUser.class)
public class DataBinderConfiguration {
    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        Map map = new HashMap<>();
        map.put(int.class, UserPropertyEditor.class);
        map.put(Date.class, DatePropertyEditor.class) ;
        customEditorConfigurer.setCustomEditors(map);
        return customEditorConfigurer;
    }
    @Bean
    @ConfigurationProperties("user")
    public User user(){
        User user = new User();
        return user;
    }
}
