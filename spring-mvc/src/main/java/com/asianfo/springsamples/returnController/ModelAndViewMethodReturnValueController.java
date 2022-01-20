package com.asianfo.springsamples.returnController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RequestMapping("/rmav")
@Controller
public class ModelAndViewMethodReturnValueController {
    @RequestMapping("/internalResourceViewResolver")
    public ModelAndView internalResourceViewResolver(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("abc","abcvalue");
        modelAndView.setViewName("/indexc.html");
        return modelAndView;
    }
    @RequestMapping("/beanNameViewResolver")
    public ModelAndView beanNameViewResolver(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("abc","abcvalue");
        modelAndView.setViewName("beanNameView");
        return modelAndView;
    }
    @RequestMapping("/forward")
    public ModelAndView forward(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("abc","abcvalue");
        modelAndView.setViewName("common");
        return modelAndView;
    }
    @RequestMapping("/common")
    public @ResponseBody String common(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("abc","abcvalue");
        return "/indexc.html";
    }
}
