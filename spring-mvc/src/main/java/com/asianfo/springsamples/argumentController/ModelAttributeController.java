package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/ma")
public class ModelAttributeController {
    @RequestMapping("/common")
    public @ResponseBody
    JSONObject common(User user){
        System.out.println("user = " + user);
        return new JSONObject();
    }
    @RequestMapping("/json")
    public @ResponseBody
    JSONObject common(JSONObject user){
        System.out.println("user = " + user);
        return new JSONObject();
    }
    @RequestMapping("/map")
    public @ResponseBody
    JSONObject map(Map user){
        System.out.println("user = " + user);
        return new JSONObject();
    }
    @ModelAttribute
    public void getUser(Model model, User user, String id, HttpServletRequest request) {
        System.out.println("ModelAttribute user = " + user + "\t" + id);
        System.out.println("request = " + request.getHeader("abc"));
        model.addAttribute("newUser",user);
    }
    @RequestMapping("/attr")
    public @ResponseBody
    JSONObject attr(@ModelAttribute("newUser") User user){
        System.out.println("attr user = " + user);
        return new JSONObject();
    }
}
