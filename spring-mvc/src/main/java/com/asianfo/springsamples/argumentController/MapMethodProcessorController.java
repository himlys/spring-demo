package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/mmps")
@Controller
public class MapMethodProcessorController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(Map abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}