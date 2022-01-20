package com.asianfo.springsamples.validator;

import com.asianfo.springsamples.entity.Role;
import com.asianfo.springsamples.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Map;
import java.util.Set;

public  class RoleValidator implements MyValidator {
    @Override
    public void validate(Object target, Errors errors, Map<Class, MyValidator> validators) {
        Role p = (Role) target;
        if (p.getId() < 0) {
            errors.reject("709", "negativevalue");
        } else if (p.getId() > 110) {
            errors.reject("709", "too.darn.old");
        }
    }
}
