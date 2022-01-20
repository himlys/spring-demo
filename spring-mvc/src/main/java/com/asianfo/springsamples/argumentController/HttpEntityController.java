package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/he")
@Controller
public class HttpEntityController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(HttpEntity abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
