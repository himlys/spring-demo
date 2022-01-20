package com.asianfo.springsamples.freemarker.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class TmEnum implements Serializable {
    private String enumCode;
    private String enumFieldCode;
}
