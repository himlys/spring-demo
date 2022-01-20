package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.ParamAnnotation;
import com.asianfo.springsamples.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/mr")
@Controller
public class MyArgumentResolverController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(@ParamAnnotation User abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
