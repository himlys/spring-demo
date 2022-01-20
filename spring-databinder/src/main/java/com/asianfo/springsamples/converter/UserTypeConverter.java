package com.asianfo.springsamples.converter;

import com.asianfo.springsamples.entity.User;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;

import java.util.Properties;

public class UserTypeConverter implements Converter<Properties, User>, ConditionalConverter {

    @Override
    public User convert(Properties source) {
        User user = new User();
        user.setId(Integer.parseInt(source.getProperty("id")));
        user.setName(source.getProperty("name"));
        return user;
    }

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return sourceType.getType().equals(Properties.class) && targetType.getType().equals(User.class);
    }
}
