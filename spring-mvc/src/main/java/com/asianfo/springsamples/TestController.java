package com.asianfo.springsamples;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.NewUser;
import com.asianfo.springsamples.entity.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class TestController {
    @InitBinder
    public void init(WebDataBinder binder, HttpServletRequest request){
        System.out.println(request.getParameter("date"));
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"),false));
    }
    @RequestMapping(value = "/test",method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody String t(){
        String r =  new Random().nextDouble() + "";
        r = "123";
        System.out.println("r = " + r);
        return r;
    }
    @RequestMapping(value = "/ac",method = {RequestMethod.POST})
    public @ResponseBody String ts(){
        String r =  new Random().nextDouble() + "";
        r = "123";
        System.out.println("r = " + r);
        return r;
    }
    @RequestMapping(value = "/t",method = {RequestMethod.POST,RequestMethod.GET})
    @CrossOrigin(origins = {"http://localhost:12309"})
    public @ResponseBody String tt(){
        String r =  new Random().nextDouble() + "";
        r = "123";
        System.out.println("r = " + r);
        return r;
    }
    @RequestMapping(value = "/beant",method = {RequestMethod.POST,RequestMethod.GET})
    public @ResponseBody User bean(@RequestBody User user){
        System.out.println("r = " + user);
        return user;
    }
    @RequestMapping(value = "/js",method = {RequestMethod.POST,RequestMethod.GET})
    @CrossOrigin(origins = {"http://localhost:12309"})
    public @ResponseBody String js(HttpServletResponse response){
        String r =  new Random().nextDouble() + "";
        r = "123";
        try {
            response.getOutputStream().write("alert(321)".getBytes(StandardCharsets.UTF_8));
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/findex",method = {RequestMethod.POST,RequestMethod.GET})
    public String freemarker(HttpServletResponse response){
        return "findex";
    }
}
