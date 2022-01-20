package com.asianfo.springsamples.conversionService;

import com.asianfo.springsamples.entity.User;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.util.Properties;

public class UserConversionService implements ConversionService {

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return sourceType.equals(Properties.class) && targetType.equals(User.class);
    }

    @Override
    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return false;
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        User user = new User();
        user.setId(Integer.parseInt(((Properties)source).getProperty("id")));
        user.setName(((Properties)source).getProperty("name"));
        return (T)user;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return null;
    }
}
