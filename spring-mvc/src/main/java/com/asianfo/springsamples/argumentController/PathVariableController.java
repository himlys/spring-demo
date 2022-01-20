package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/p/{abc}")
public class PathVariableController {
    @RequestMapping("/aqa/{1}")
    public @ResponseBody
    JSONObject aqa(@PathVariable("1") String a,@PathVariable("abc") String b){
        System.out.println("aqa = " + a + "\t" + b);
       return null;
    }
    @RequestMapping("/aqb/key/{1}/value/{2}")
    public @ResponseBody
    JSONObject aqb(@PathVariable Map a){
        System.out.println("aqb = " + a);
        return null;
    }

    @RequestMapping("/aqc/{1}")
    public @ResponseBody
    JSONObject aqc(@PathVariable("1") Optional<String> a){
        System.out.println("aqa = " + a.get());
        return null;
    }
    @RequestMapping("/aqd/key/{1}/value/{2}")
    public @ResponseBody
    JSONObject aqd(@PathVariable Map a){
        System.out.println("aqb = " + a);
        return null;
    }
    @RequestMapping("/aqe/{name:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]+}")
    public @ResponseBody String aqe(@PathVariable String version, @PathVariable String extension) {
        System.out.println(version + "\t" + extension);
        return new String("success");
    }
}
