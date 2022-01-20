package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/scvm")
@Controller
public class ServletCookieValueMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(@CookieValue String abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
