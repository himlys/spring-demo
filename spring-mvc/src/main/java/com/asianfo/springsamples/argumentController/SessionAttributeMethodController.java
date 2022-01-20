package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/sa")
@Controller
public class SessionAttributeMethodController {
    @InitBinder
    private void init(HttpServletRequest request){
        request.getSession().setAttribute("abc","abcvalue");
    }
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(User user,@SessionAttribute String abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
    @RequestMapping("/c")
    public @ResponseBody
    JSONObject c(@SessionAttribute String abc,User user){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
