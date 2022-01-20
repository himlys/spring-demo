package com.asianfo.springsamples.validator;

import org.springframework.validation.Errors;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public interface MyValidator {
    void validate(Object target, Errors errors, Map<Class, MyValidator> validators);
}
