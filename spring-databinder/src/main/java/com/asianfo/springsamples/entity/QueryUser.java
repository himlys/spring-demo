package com.asianfo.springsamples.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("queryuser")
@Data
@Component
public class QueryUser {
    private int id;
}
