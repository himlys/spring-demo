package com.asianfo.springsamples.freemarker.keyGenerator.impl;

import com.asianfo.springsamples.freemarker.keyGenerator.KeyGenerator;
import org.springframework.stereotype.Component;

@Component("productKeyGenerator")
public class ProductKeyGenerator implements KeyGenerator {
    @Override
    public String generate(Object[] args) {
        return args[0]+"";
    }
}
