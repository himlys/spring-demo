package com.asianfo.springsamples;

import com.asianfo.springsamples.entity.User;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.TomcatServletWebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpMethod;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.util.PathMatcher;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;
import org.springframework.web.servlet.resource.AbstractResourceResolver;
import org.springframework.web.servlet.resource.EncodedResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableWebMvc

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        System.out.println("Hello World!" + ClassUtils.isPrimitiveOrWrapper(User.class));
    }

    @Bean
    public WebMvcConfigurer WebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void configureViewResolvers(ViewResolverRegistry registry) {
//                registry.viewResolver(new InternalResourceViewResolver());
            }

            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new HandlerMethodArgumentResolver() {
                    @Override
                    public boolean supportsParameter(MethodParameter parameter) {
                        // 这里可以使用注解的方式去区分，或者更暴力，直接使用ClassType
                        return parameter.getParameterType() == User.class || parameter.getParameterAnnotation(ParamAnnotation.class) != null;
                    }

                    @Override
                    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//                        简单的处理一下，弄出一个对象来。
//                        String abc = webRequest.getParameter("abc");
//                        User user = new User();
//                        user.setId(abc);
//                        return user;
//                        我们也可以将redis中的用户信息，在这里，统一处理一下，给Controller传过去。不建议这么做（后面缓存章节，会详细介绍原因）
                        User user = new User();
                        String[] tokens = webRequest.getHeaderValues("TOKEN");
                        if(tokens != null && tokens.length > 0) {
                            String token = tokens[0];
                            // 用token 去redis里查询出userInfo。
//                            user = xxx.getInfo();
                        }
                        return user;
                    }
                });
            }


            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper pathHelper = new UrlPathHelper();
                pathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(pathHelper);
            }

            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer.mediaTypes(new HashMap<>());
            }

            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
                configurer.enable("dServletName");
            }
//            @Override
//            public void configurePathMatch(PathMatchConfigurer configurer) {
//                configurer.setPathMatcher(new PathMatcher() {
//                    @Override
//                    public boolean isPattern(String path) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean match(String pattern, String path) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean matchStart(String pattern, String path) {
//                        return false;
//                    }
//
//                    @Override
//                    public String extractPathWithinPattern(String pattern, String path) {
//                        return null;
//                    }
//
//                    @Override
//                    public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
//                        return null;
//                    }
//
//                    @Override
//                    public Comparator<String> getPatternComparator(String path) {
//                        return null;
//                    }
//
//                    @Override
//                    public String combine(String pattern1, String pattern2) {
//                        return null;
//                    }
//                });
//            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/")
                        .setCacheControl(CacheControl.maxAge(20, TimeUnit.SECONDS))
                        .resourceChain(false)
//                        .addResolver(new EncodedResourceResolver())
                        .addResolver(new VersionResourceResolver().addFixedVersionStrategy("1.0.0", "/**/index.js"))
//                        .setCacheControl(CacheControl.noCache())
//                        .setCachePeriod(2)
//                        .setUseLastModified(false)
                ;
                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations(
                                "classpath:/META-INF/resources/webjars/");
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/abc")
                        .allowedOrigins("a")
                        .maxAge(20)
                        .allowedHeaders("abc")
                        .allowedMethods("post");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addWebRequestInterceptor(new WebRequestInterceptor() {
                    @Override
                    public void preHandle(WebRequest request) throws Exception {

                    }

                    @Override
                    public void postHandle(WebRequest request, ModelMap model) throws Exception {

                    }

                    @Override
                    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

                    }
                });
                registry.addInterceptor(new MappedInterceptor(new String[]{"/a.js"}, new HandlerInterceptor() {
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                            throws Exception {

                        return true;
                    }

                    public boolean postHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                            throws Exception {

                        return true;
                    }

                    public boolean afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler)
                            throws Exception {

                        return true;
                    }
                }));
                WebMvcConfigurer.super.addInterceptors(registry);
            }
            //            @Override
//            public void addCorsMappings(CorsRegistry registry){
//                registry.addMapping("/t").allowedOrigins("http://localhost:18080");
//            }
        };
    }

    //    @Bean
//    public CorsFilter corsFilter(CorsConfigurationSource corsConfigurationSource){
//        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource);
//        return corsFilter;
//    }
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource(){
//        return (request) -> {
//            CorsConfiguration corsConfiguration = new CorsConfiguration();
//            corsConfiguration.addAllowedOrigin("http://localhost:18080");
//            corsConfiguration.addAllowedHeader("*");
//            corsConfiguration.addAllowedMethod(HttpMethod.GET);
//            corsConfiguration.addAllowedMethod(HttpMethod.POST);
//            return corsConfiguration;
//        };
//    }
    @Bean
    public TomcatConnectorCustomizer tomcatConnectorCustomizer() {
        return (connector) -> {
            connector.setPort(10223);
        };
    }

    @Bean
    public TomcatContextCustomizer tomcatContextCustomizer() {
        return (context) -> {

        };
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            factory.setContextPath("/headercheck");
        };
    }

    //    @Bean
//    public OncePerRequestFilter etagFilter(){
//        return new ShallowEtagHeaderFilter();
//    }
    @Bean
    public OncePerRequestFilter oncePerRequestFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                response.setHeader("Access-Control-Allow-Origin", "http://localhost:18080");
//                response.setHeader("Access-Control-Allow-Methods","*");
                response.setHeader("Access-Control-Allow-Methods", "GET");
//                response.setHeader("Access-Control-Allow-Headers","X-User-Param");
//                response.setHeader("Access-Control-Allow-Headers","*");
                response.setHeader("Access-Control-Allow-Headers", "X-User-Param");
                response.setHeader("Access-Control-Allow-Headers", "Accept,Content-Type,x-user-param");
//                response.setHeader("Access-Control-Max-Age","600");
                response.setHeader("TOKEN", "6010");
                response.setHeader("Access-Control-Expose-Headers", "TOKEN");

                response.setHeader("Cache-Control", "max-age=20");
//                response.setDateHeader("Expires",System.currentTimeMillis() + 1000 * 3600);
//                response.setHeader("Cache-Control","no-cache");
//                response.setHeader("Cache-Control","no-store");
//                response.setHeader("Access-Control-Allow-Credentials","true");
                response.setHeader("X-Content-Type-Options", "nosniff");
//                response.setHeader("Content-Security-Policy","frame-ancestors http://localhost:18080");
                response.setHeader("Link", "<https://localhost:10223/headercheck/index.css>;rel=stylesheet");
                Cookie c1 = new Cookie("username2", "hzh");
                c1.setHttpOnly(true);
                response.addCookie(c1);
                filterChain.doFilter(request, response);

            }
        };
    }
}
