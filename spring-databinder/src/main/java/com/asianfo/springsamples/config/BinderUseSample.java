package com.asianfo.springsamples.config;

import com.asianfo.springsamples.entity.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.BeanFactoryAnnotationUtils;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.bind.*;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.env.Environment;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Component
public class BinderUseSample implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        // 这种获取Binder的方式，是无法使用容器中的PropertyEditor和Converter的。
        Binder binder = Binder.get(applicationContext.getEnvironment());
        List<String> s = binder.bind("b.id", String[].class).map(Arrays::asList)
                .orElse(Collections.emptyList());
        System.out.println(s);
        // 因为无法使用PropertyEditor，user.id=A，所以直接就报错了。
        // 注解了，因为会报错
//        BindResult bindResult = binder.bind("user.id", Bindable.of(int.class));
//        Object o = bindResult.get();
//        System.out.println(o);
        // 想要使用PropertyEditor的话。
        Iterable<ConfigurationPropertySource> sources = ConfigurationPropertySources.get(applicationContext.getEnvironment());
        PropertySourcesPlaceholdersResolver placeholdersResolver = new PropertySourcesPlaceholdersResolver(applicationContext.getEnvironment());
        Binder newBinder = new Binder(sources, placeholdersResolver,
                get(applicationContext), ((ConfigurableApplicationContext) applicationContext).getBeanFactory()::copyRegisteredEditorsTo, null,
                null);
        BindResult br = newBinder.bind("user.id", Bindable.of(int.class));
        Object r = br.get();
        System.out.println(r);
        User user = newBinder.bind("user",Bindable.of(User.class)).get();
        System.out.println("userbind = " + user);
    }
    private ConversionService get(ListableBeanFactory beanFactory){
        final List<Converter> converters;
        final List<GenericConverter> genericConverters;
        @SuppressWarnings("rawtypes")
        final List<Formatter> formatters;
        converters = beans(beanFactory, Converter.class, ConfigurationPropertiesBinding.VALUE);
        genericConverters = beans(beanFactory, GenericConverter.class, ConfigurationPropertiesBinding.VALUE);
        formatters = beans(beanFactory, Formatter.class, ConfigurationPropertiesBinding.VALUE);
        if (converters.isEmpty() && genericConverters.isEmpty() && formatters.isEmpty()) {
            return ApplicationConversionService.getSharedInstance();
        }
        ApplicationConversionService conversionService = new ApplicationConversionService();
        for (Converter<?, ?> converter : converters) {
            conversionService.addConverter(converter);
        }
        for (GenericConverter genericConverter : genericConverters) {
            conversionService.addConverter(genericConverter);
        }
        for (Formatter<?> formatter : formatters) {
            conversionService.addFormatter(formatter);
        }
        return conversionService;
    }
    private <T> List<T> beans(BeanFactory beanFactory, Class<T> type, String qualifier) {
        if (beanFactory instanceof ListableBeanFactory) {
            return beans(type, qualifier, (ListableBeanFactory) beanFactory);
        }
        return Collections.emptyList();
    }
    private <T> List<T> beans(Class<T> type, String qualifier, ListableBeanFactory beanFactory) {
        return new ArrayList<>(
                BeanFactoryAnnotationUtils.qualifiedBeansOfType(beanFactory, type, qualifier).values());
    }
}
