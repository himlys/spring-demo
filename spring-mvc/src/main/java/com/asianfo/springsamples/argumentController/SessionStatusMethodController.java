package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

@RequestMapping("/ss")
@Controller
public class SessionStatusMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(SessionStatus sessionStatus){
        System.out.println("sessionStatus = " + sessionStatus);
        return new JSONObject();
    }
}
