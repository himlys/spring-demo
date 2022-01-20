package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rb")
public class RequestBodyController {
    @RequestMapping("/a")
    public @ResponseBody
    JSONObject aqa(@RequestBody User user){
        System.out.println("rbq = " + user);
        return new JSONObject();
    }
    @RequestMapping("/s")
    public @ResponseBody
    JSONObject aqb(@RequestBody  String r){
        System.out.println("rbb = " + r);
        return new JSONObject();
    }
    @RequestMapping("/b")
    public @ResponseBody
    JSONObject b(@RequestBody  byte[] r){
        System.out.println("byte[] = " + new String(r));
        return new JSONObject();
    }
}
