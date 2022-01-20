package com.asianfo.springsamples.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.Map;

public class SpringValidator implements Validator {
    Map<Class, MyValidator> validators = null;

    public SpringValidator() {

    }

    public SpringValidator(Map<Class, MyValidator> validators) {
        this.validators = validators;
    }

    // 可以搞成配置的，不过，没必要，直接写死即可，想要添加的时候再加。
    @Override
    public boolean supports(Class<?> clazz) {
        return validators.keySet().contains(clazz) || Collection.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Collection) {
            if(target != null && ((Collection<?>) target).size() > 0){
                ((Collection<?>) target).stream().forEach(o -> {
                    MyValidator v = validators.get(o.getClass());
                    if(v != null ){
                        v.validate(o,errors,validators);
                    }
                });
            }
        } else if (target.getClass().isArray()) {

        } else {
            MyValidator v = validators.get(target.getClass());
            if(v != null ){
                v.validate(target,errors,validators);
            }
        }
    }
}
