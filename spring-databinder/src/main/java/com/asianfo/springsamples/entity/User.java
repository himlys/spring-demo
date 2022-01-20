package com.asianfo.springsamples.entity;

import lombok.Data;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class User {
    int id;
    String name;
    List<Role> roles;
    Date birth;
}
