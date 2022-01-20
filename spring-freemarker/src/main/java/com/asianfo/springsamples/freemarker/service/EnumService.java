package com.asianfo.springsamples.freemarker.service;

import com.asianfo.springsamples.freemarker.entity.TmEnum;

import java.util.List;

public interface EnumService {
    List<TmEnum> getEnum(String enumCode);
}
