package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class RequestParamController {
    @RequestMapping("/ssa")
    public @ResponseBody
    JSONObject ssa(@RequestParam("a") String a, @RequestParam("b") String b, HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        String p = request.getParameter("");
        System.out.println("0 = " + a + "\t" + b + "\t");
        return new JSONObject();
    }
    @RequestMapping("/ssb")
    public @ResponseBody
    JSONObject ssb(@RequestParam("name") String a, @RequestParam("file") List<MultipartFile> b, HttpServletRequest request) {
        String[] names = request.getParameterValues("name");
        Map<String, String[]> map = request.getParameterMap();
        map.entrySet().stream().map(stringEntry -> {
            String[] s = stringEntry.getValue();
            System.out.println(s);
            return stringEntry;
        });
        System.out.println(names);
        System.out.println("0 = " + a + "\t" + b);
        return new JSONObject();
    }
    @RequestMapping("/ssc")
    public @ResponseBody
    JSONObject ssc(@RequestParam("name") String a,@RequestParam("file") List<MultipartFile> b) {
        System.out.println("0 = " + a + "\t" + b);
        return new JSONObject();
    }
    @RequestMapping("/ssd")
    public @ResponseBody
    JSONObject ssd(@RequestParam("name") String a,@RequestBody JSONObject o) {
        System.out.println("0 = " + a + "\t" + o);
        return new JSONObject();
    }
    @RequestMapping("/sse")
    public @ResponseBody
    JSONObject sse(@RequestParam("name") String name,@RequestParam("id") String id) {
        System.out.println("01 = " + "\t" + name);
        return new JSONObject();
    }
    @RequestMapping("/ssf")
    public @ResponseBody
    JSONObject ssf(@RequestParam Map<String,Map<String,String>> o) {
        Object s = o.get("name");
        System.out.println(s.getClass());
        System.out.println("010 = " + "\t" + o);
        return new JSONObject();
    }
    @RequestMapping("/sss")
    public @ResponseBody
    JSONObject sss(List<MultipartFile> b) {
        System.out.println("0 = "  + b);
        return new JSONObject();
    }
}
