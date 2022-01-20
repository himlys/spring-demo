package com.asianfo.springsamples;

import com.asianfo.springsamples.conversionService.UserConversionService;
import com.asianfo.springsamples.converter.UserTypeConverter;
import com.asianfo.springsamples.entity.User;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Properties;

public class UserTypeConverterTest {
    public static void main(String[] args) {
        UserTypeConverter service = new UserTypeConverter();
        Properties properties = new Properties();
        properties.setProperty("id", "1");
        properties.setProperty("name", "tomcat");

        if(service.matches(TypeDescriptor.forObject(properties),TypeDescriptor.valueOf(User.class))) {
            User user = service.convert(properties);
            System.out.println(user);
        }
    }
}
