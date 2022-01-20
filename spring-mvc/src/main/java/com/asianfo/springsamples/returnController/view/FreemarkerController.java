package com.asianfo.springsamples.returnController.view;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("/fmc")
@Controller
public class FreemarkerController {
    @RequestMapping("/html")
    public ModelAndView forward(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("abc","abcvalue");
        modelAndView.addObject("user",new User("123","456"));
        modelAndView.addObject("userdate",new Date());
        List<User> userList = new ArrayList<>();
        userList.add(new User("1","1号用户"));
        userList.add(new User("2","2号用户"));
        modelAndView.addObject("userList",userList);
        List<User> userList2 = new ArrayList<>();
        userList2.add(new User("1",null));
        userList2.add(new User("2","2号用户"));
        modelAndView.addObject("userList2",userList2);
        List<User> userList3 = new ArrayList<>();
        modelAndView.addObject("userList3",userList3);
        JSONArray array = new JSONArray();
        JSONObject o = new JSONObject();
        o.put("name","myname");
        array.add(o);
        modelAndView.addObject("userList4",array);
        modelAndView.addObject("userMap",o);
        modelAndView.setViewName("/findex");
        return modelAndView;
    }
}
