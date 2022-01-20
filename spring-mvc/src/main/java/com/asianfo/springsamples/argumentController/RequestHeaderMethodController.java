package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/rh")
@Controller
public class RequestHeaderMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(@RequestHeader String abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
