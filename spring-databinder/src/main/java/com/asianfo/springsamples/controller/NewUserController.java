package com.asianfo.springsamples.controller;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.NewUser;
import com.asianfo.springsamples.entity.User;
import com.asianfo.springsamples.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;

@Controller
@RequestMapping("/newuser")
public class NewUserController {
    @Autowired
    User user;
    @PostConstruct
    public void init(){
        System.out.println("user = " + user);
    }
    @RequestMapping("/a")
    public @ResponseBody  JSONObject a( @Validated NewUser user){
        System.out.println(user);
        return new JSONObject();
    }
    @RequestMapping("/b")
    public @ResponseBody  JSONObject b(@RequestBody @Validated ArrayList<NewUser> user){
        System.out.println(user);
        return new JSONObject();
    }
    @RequestMapping("/c")
    public @ResponseBody  JSONObject c(@RequestBody @Validated NewUser user){
        System.out.println(user);
        return new JSONObject();
    }
    @InitBinder
    public void b(WebDataBinder dataBinder){
        dataBinder.registerCustomEditor(Integer.class,"id",new PropertyEditorSupport(){
            private Object value;
            public void setAsText(String text) throws java.lang.IllegalArgumentException {
                if(text.equals("A")){
                    setValue(120);
                    return;
                }

                setValue(text);
            }
            public void setValue(Object value) {
                this.value = value;
                firePropertyChange();
            }
            public String getAsText() {
                return (this.value != null)
                        ? this.value.toString()
                        : null;
            }
            public Object getValue() {
                return value;
            }
        });
    }
//    @InitBinder
//    public void i(WebDataBinder dataBinder){
//        dataBinder.registerCustomEditor(Integer.class, "id", new PropertyEditor() {
//            private Object value;
//
//            @Override
//            public void setValue(Object value) {
//                this.value = value;
//            }
//
//            @Override
//            public Object getValue() {
//                return value;
//            }
//
//            @Override
//            public boolean isPaintable() {
//                return false;
//            }
//
//            @Override
//            public void paintValue(Graphics gfx, Rectangle box) {
//
//            }
//
//            @Override
//            public String getJavaInitializationString() {
//                return null;
//            }
//
//            @Override
//            public String getAsText() {
//                return null;
//            }
//
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                if(text.equals("A")){
//                    setValue(120);
//                }
//            }
//
//            @Override
//            public String[] getTags() {
//                return new String[0];
//            }
//
//            @Override
//            public Component getCustomEditor() {
//                return null;
//            }
//
//            @Override
//            public boolean supportsCustomEditor() {
//                return false;
//            }
//
//            @Override
//            public void addPropertyChangeListener(PropertyChangeListener listener) {
//
//            }
//
//            @Override
//            public void removePropertyChangeListener(PropertyChangeListener listener) {
//
//            }
//        });
//        dataBinder.setValidator(new Validator() {
//            @Override
//            public boolean supports(Class<?> clazz) {
//                return NewUser.class.equals(clazz);
//            }
//
//            @Override
//            public void validate(Object target, Errors errors) {
//                NewUser p = (NewUser) target;
//                if (p.getId() < 0) {
//                    errors.reject("109","用户ID小于0");
//                } else if (p.getId() > 110) {
//                    errors.reject("109","用户ID大于110");
//                }
//            }
//        });
//    }
}
