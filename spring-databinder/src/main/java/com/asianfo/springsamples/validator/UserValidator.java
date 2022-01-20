package com.asianfo.springsamples.validator;

import com.asianfo.springsamples.entity.Role;
import com.asianfo.springsamples.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public  class UserValidator implements MyValidator {

    @Override
    public void validate(Object target, Errors errors, Map<Class, MyValidator> validators) {
        // 不能使用errors提供的各种校验了，因为errors中有beanWrapper，里面的类型可能还是集合类型（如List）
//        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        User p = (User) target;
        if (p.getId() < 0) {
            errors.reject("109","用户ID小于0");
        } else if (p.getId() > 110) {
            errors.reject("109","用户ID大于110");
        }
        List<Role> roles = ((User) target).getRoles();
        if(roles != null && roles.size() > 0) {
            for(Role s : roles) {
                MyValidator v = validators.get(s.getClass());
                if(v != null ){
                    v.validate(s,errors,validators);
                }
            }
        }
    }
}
