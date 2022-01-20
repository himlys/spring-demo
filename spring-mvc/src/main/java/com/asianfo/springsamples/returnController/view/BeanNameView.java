package com.asianfo.springsamples.returnController.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class BeanNameView implements View {
    private static final String ContentType = "text/html;charset=utf-8";

    @Override
    public  String getContentType() {
        return ContentType;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(ContentType);
        response.getOutputStream().write(new String("Hello BeanNameView!").getBytes(StandardCharsets.UTF_8));
    }
}
