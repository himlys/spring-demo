package com.asianfo.springsamples.servlet;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@ServletComponentScan
public class ServletEnhanceConfiguration {
    @WebServlet(name = "aa", urlPatterns = "/aa")
    static class AServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("第一个");
            resp.getOutputStream().write("Success".getBytes(StandardCharsets.UTF_8));
        }

    }
}
@WebServlet(name = "a", urlPatterns = "/a")
class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("第一个");
        resp.getOutputStream().write("Success".getBytes(StandardCharsets.UTF_8));
    }

}

@WebServlet(name = "c", urlPatterns = "/c")
class CServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("第三个");
        resp.getOutputStream().write("Success".getBytes(StandardCharsets.UTF_8));
    }

}