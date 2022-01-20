package com.asianfo.springsamples.filter;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

@Configuration
public class FilterAutoConfiguration {
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean<Filter>(new Filter() {
            //            @Autowired
//            ApplicationContext applicationContext;
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                System.out.println("Filter c");
                chain.doFilter(request, response);
//                System.out.println(applicationContext.getApplicationName());
            }
        });
        filterRegistration.setUrlPatterns(Collections.singleton("/c"));
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy("filterFactoryBean");
        filterRegistration.setFilter(proxy);
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/c");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }

    @Bean
    public FilterRegistrationBean targetProxyC() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy("filterFactoryBean");
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy(new HttpFilter() {
////            @Autowired
////            ApplicationContext applicationContext;
//
//            @Override
//            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//                super.doFilter(request, response, chain);
//                // 报错，因为是new的filter，不会使用spring去管理这个filter的bean
////                System.out.println(applicationContext.getApplicationName());
//                System.out.println("直接");
//            }
//        });
        filterRegistration.setFilter(proxy);
        filterRegistration.setName("targetProxyC");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/c");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }
    @Bean
    public FilterRegistrationBean targetProxyCF() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy(targetProxyCFilter());
        DelegatingFilterProxy proxy = new DelegatingFilterProxy("targetProxyCFilter");
        proxy.setTargetFilterLifecycle(true);
        filterRegistration.setFilter(proxy);
        filterRegistration.setName("targetProxyCD");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/c");
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
    }
    @Bean
    public HttpFilter targetProxyCFilter() {
        return new HttpFilter() {
                        @Autowired
            ApplicationContext applicationContext;
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                System.out.println("Filter targetProxyCFilter");
                System.out.println("applicationContext = " + applicationContext.getApplicationName());
                chain.doFilter(request, response);
            }
        };
    }
    @Bean("filterFactoryBean")
    public FilterFactoryBean filterFactoryBean() {
        return new FilterFactoryBean();
    }

    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean(ServletRegistrationBean getServletRegistrationBean) {
        return new DelegatingFilterProxyRegistrationBean("dd", getServletRegistrationBean);
    }

    @Bean
    public HttpFilter dd() {
        return new HttpFilter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                System.out.println("Filter dd");
                chain.doFilter(request, response);
            }
        };
    }
    @Bean
    public FilterRegistrationBean abcd(Filter specialFilter) {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean<Filter>(specialFilter);
        filterRegistration.setUrlPatterns(Collections.singleton("/c"));
        filterRegistration.setName("specialFilter");
        return filterRegistration;
    }
    @Bean
    public Filter specialFilter(){
        return new HttpFilter() {
            @Autowired
            ApplicationContext applicationContext;
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                super.doFilter(request, response, chain);
                ((HttpServletRequest)request).getSession();
                System.out.println("applicationContextabc = " + applicationContext.getApplicationName());
            }
        };
    }
}

class FilterFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return new HttpFilter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                super.doFilter(request, response, chain);
                System.out.println("factory");
            }
        };
    }

    @Override
    public Class<?> getObjectType() {
        return HttpFilter.class;
    }
}
