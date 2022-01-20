package com.asianfo.out.config;

import com.asianfo.springsamples.entity.NewUser;
import com.asianfo.springsamples.entity.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableConfigurationProperties(NewUser.class)
public class OutConfiguration {
    @Bean
    @ConfigurationProperties("user2")
    public NewUser newUser(){
        NewUser user = new NewUser();
        return user;
    }
}
