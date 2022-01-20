package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rp")
public class RequestPartMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(@RequestPart String file,String json){
        System.out.println("user = " + json);
        return new JSONObject();
    }
    @RequestMapping("/c")
    public @ResponseBody
    JSONObject c(@RequestPart String file,String json){
        System.out.println("user = " + json);
        return new JSONObject();
    }
}
