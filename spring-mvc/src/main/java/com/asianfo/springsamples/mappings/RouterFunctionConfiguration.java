package com.asianfo.springsamples.mappings;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.function.*;
@Configuration
public class RouterFunctionConfiguration {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route(RequestPredicates.path("/abc/**").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)).and(RequestPredicates.param("a","1")),new HandlerFunction<ServerResponse>(){

            @Override
            public ServerResponse handle(ServerRequest request) throws Exception {
                JSONObject o = request.body(JSONObject.class);
                System.out.println("没有入参 " + o);
                return ServerResponse.ok().body("success response");
            }
        });
    }
    @Bean
    public RouterFunction<ServerResponse> routerFunction2(){
        return RouterFunctions.route(RequestPredicates.path("/xyz/**"),new HandlerFunction<ServerResponse>(){

            @Override
            public ServerResponse handle(ServerRequest request) throws Exception {
                System.out.println("没有入参2 ");
                return ServerResponse.ok().body("success response2");
            }
        });
    }
    @Bean
    public RouterFunction<ServerResponse> routerFunction3(){
        return RouterFunctions.route(RequestPredicates.path("/opq/**").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)).and(RequestPredicates.param("a","1")),new HandlerFunction<ServerResponse>(){

            @Override
            public ServerResponse handle(ServerRequest request) throws Exception {
                JSONObject o = request.body(JSONObject.class);
                System.out.println("没有入参3 " + o);
                return ServerResponse.ok().body("success response3");
            }
        });
    }
    @Bean
    public RouterFunction<ServerResponse> routerFunction4(){
        return RouterFunctions.route(RequestPredicates.path("/op4q/**").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)).and(RequestPredicates.param("a","1")),new HandlerFunction<ServerResponse>(){

            @Override
            public ServerResponse handle(ServerRequest request) throws Exception {
                JSONObject o = request.body(JSONObject.class);
                System.out.println("没有入参34 " + o);
                return ServerResponse.ok().body("success response3");
            }
        });
    }
}
