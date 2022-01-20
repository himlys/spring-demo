package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/sr")
@Controller
public class ServletRequestMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(HttpServletRequest abc, HttpServletResponse response){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
