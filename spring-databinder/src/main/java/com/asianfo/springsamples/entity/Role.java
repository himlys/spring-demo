package com.asianfo.springsamples.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Role {
    int id;
    List<String> f=new ArrayList<>();
}
