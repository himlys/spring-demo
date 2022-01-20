package com.asianfo.springsamples.argumentController;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/m")
public class MatrixVariableController {
    @RequestMapping("/aqa/{name}/{id}")
    public @ResponseBody
    JSONObject aqa(@MatrixVariable(pathVar = "name") String name, @MatrixVariable(pathVar = "id") String id){
        System.out.println("aqa = " + name + "\t" + id);
        return new JSONObject();
    }
    @RequestMapping("/aqb/{name}/{id}/{p}")
    public @ResponseBody
    JSONObject aqb(@MatrixVariable(pathVar = "name") String name, @MatrixVariable(pathVar = "id") String id, @MatrixVariable(pathVar = "p") Map<String, List<String>> params){
        System.out.println("aqa = " + name + "\t" + id);
        System.out.println(params);
        return new JSONObject();
    }
}
