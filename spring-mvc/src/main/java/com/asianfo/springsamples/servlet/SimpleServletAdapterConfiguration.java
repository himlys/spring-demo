package com.asianfo.springsamples.servlet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleServletHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SimpleServletAdapterConfiguration {
    @Bean
    public SimpleServletHandlerAdapter simpleServletHandlerAdapter(){
        return new SimpleServletHandlerAdapter();
    }
    @Bean("dServletName")
    public HttpServlet httpServletAdapterC(){
        return new HttpServlet() {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                System.out.println("httpServletAdapterC");
                resp.getOutputStream().write("success dServletName".getBytes(StandardCharsets.UTF_8));
            }
        };
    }
}
