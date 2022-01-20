package com.asianfo.springsamples.servlet;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Configuration
public class ListenerConfiguration {
    @Bean
    public ServletListenerRegistrationBean registrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new ServletContextAttributeListener(){
            public void attributeAdded(ServletContextAttributeEvent scae) {
                // do something
            }
            public void attributeRemoved(ServletContextAttributeEvent scae) {
            }
            public void attributeReplaced(ServletContextAttributeEvent scae) {
            }
        });
        return servletListenerRegistrationBean;
    };
    @Bean
    public ServletListenerRegistrationBean ServletRequestListener(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new ServletRequestListener(){
            public  void requestInitialized (ServletRequestEvent sre) {
                System.out.println("requestInitialized");
            }
            public void requestDestroyed(ServletRequestEvent sre) {
                System.out.println("requestDestroyed");
            }
        });
        return servletListenerRegistrationBean;
    };
    @Bean
    public ServletListenerRegistrationBean HttpSessionListener(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new HttpSessionListener(){
            public void sessionCreated(HttpSessionEvent se) {
            }
            public void sessionDestroyed(HttpSessionEvent se) {
            }
        });
        return servletListenerRegistrationBean;
    };
    @Bean
    public ServletListenerRegistrationBean ServletContextListener(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new ServletContextListener(){
            public void contextInitialized(ServletContextEvent sce) {
            }

            public void contextDestroyed(ServletContextEvent sce) {
            }
        });
        return servletListenerRegistrationBean;
    };
}
