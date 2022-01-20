package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.ModelMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/mmp")
@Controller
public class ModelMethodProcessorController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(Model abc){
        System.out.println("abc = " + abc);
        return new JSONObject();
    }
}
