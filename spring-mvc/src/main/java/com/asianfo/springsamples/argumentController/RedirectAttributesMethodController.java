package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/ra")
@Controller
public class RedirectAttributesMethodController {
    @RequestMapping("/common")
    public
    String common(RedirectAttributes attributes){
        System.out.println("abc = " + attributes);
        attributes.addAttribute("abc","abcvalue");
        attributes.addAttribute("id","A");
        attributes.addAttribute("name","120");
        return "redirect:c";
    }
    @RequestMapping("/c")
    public @ResponseBody
    JSONObject c(String abc, User user){
        System.out.println("abc = " + abc + "\t" + user);
        return new JSONObject();
    }
}
