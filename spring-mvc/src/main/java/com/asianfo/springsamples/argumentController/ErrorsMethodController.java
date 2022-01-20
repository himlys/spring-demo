package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/em")
@Controller
public class ErrorsMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(@Validated User abc, Errors errors){
        System.out.println("errors = " + errors);
        return new JSONObject();
    }
    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.setValidator(new Validator() {
            @Override
            public boolean supports(Class<?> clazz) {
                return User.class == (clazz);
            }

            @Override
            public void validate(Object target, Errors errors) {
                errors.reject("1020","故意做一个异常出来");
                System.out.println("target" + target);
            }
        });
    }
}
