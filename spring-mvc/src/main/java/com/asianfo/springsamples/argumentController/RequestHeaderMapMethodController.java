package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/rhm")
@Controller
public class RequestHeaderMapMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(@RequestHeader Map abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
