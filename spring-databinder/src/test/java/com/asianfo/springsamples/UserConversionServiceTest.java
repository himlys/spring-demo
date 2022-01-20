package com.asianfo.springsamples;

import com.asianfo.springsamples.conversionService.UserConversionService;
import com.asianfo.springsamples.entity.User;

import java.util.Properties;

public class UserConversionServiceTest {
    public static void main(String[] args) {
        UserConversionService service = new UserConversionService();
        Properties properties = new Properties();
        properties.setProperty("id","1");
        properties.setProperty("name","tomcat");
        if(service.canConvert(Properties.class, User.class)) {
            User user = service.convert(properties,User.class);
            System.out.println(user);
        }
    }
}
