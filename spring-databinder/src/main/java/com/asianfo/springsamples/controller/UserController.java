package com.asianfo.springsamples.controller;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/a")
    public @ResponseBody  JSONObject a(@Validated User user){
        System.out.println(user);
        return new JSONObject();
    }
    @RequestMapping("/b")
    public @ResponseBody  JSONObject b(@RequestBody @Validated ArrayList<User> user){
        System.out.println(user);
        return new JSONObject();
    }
    @RequestMapping("/c")
    public ModelAndView c(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("a","b");
        return modelAndView;
    }
}
