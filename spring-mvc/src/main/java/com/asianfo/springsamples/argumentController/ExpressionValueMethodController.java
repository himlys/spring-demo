package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/ev")
@Controller
public class ExpressionValueMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(@Value("${expression.value}") String abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
