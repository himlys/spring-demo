package com.asianfo.springsamples.business;

import com.asianfo.springsamples.entity.NewUser;
import com.asianfo.springsamples.entity.OldUser;
import com.asianfo.springsamples.entity.QueryUser;
import com.asianfo.springsamples.entity.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserBusiness implements InitializingBean {
    @Value(("${user.id}"))
    private int id;
    @Autowired
    private User user;
    @Autowired
    private NewUser newUser;
    @Autowired
    private OldUser oldUser;
    @Autowired
    private QueryUser queryUser;
    public UserBusiness(){
        System.out.println(111);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("userBusiness user = " + user);
        System.out.println("userBusiness newUser = " + newUser);
        System.out.println("userBusiness oldUser = " + oldUser);
        System.out.println("userBusiness queryUser = " + queryUser);
    }
    @PostConstruct
    private void init(){

    }
}
