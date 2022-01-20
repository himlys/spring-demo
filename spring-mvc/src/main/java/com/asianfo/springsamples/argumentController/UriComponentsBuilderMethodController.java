package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@RequestMapping("/ucb")
@Controller
public class UriComponentsBuilderMethodController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(UriComponentsBuilder uriComponentsBuilder, ServletUriComponentsBuilder servletUriComponentsBuilder){
        System.out.println("uriComponentsBuilder = " + uriComponentsBuilder);
        return new JSONObject();
    }
}
