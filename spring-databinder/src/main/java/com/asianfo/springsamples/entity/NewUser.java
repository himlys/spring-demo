package com.asianfo.springsamples.entity;

import lombok.Data;

import java.util.List;

@Data
public class NewUser {
    int id;
    String name;
    List<Role> roles;
}
