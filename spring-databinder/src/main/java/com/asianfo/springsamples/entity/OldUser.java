package com.asianfo.springsamples.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("olduser")
@Data
public class OldUser {
    private int id;
}
