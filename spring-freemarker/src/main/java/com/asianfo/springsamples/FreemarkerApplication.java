package com.asianfo.springsamples;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.asianfo.springsamples.freemarker.mapper")
public class FreemarkerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(FreemarkerApplication.class,args);
        System.out.println( "Hello World!" );
    }
    @Bean
    public TomcatConnectorCustomizer tomcatConnectorCustomizer(){
        return connector -> connector.setPort(12098);
    }
}
