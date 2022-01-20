package com.asianfo.springsamples.freemarker.service.impl;

import com.asianfo.springsamples.freemarker.entity.TmEnum;
import com.asianfo.springsamples.freemarker.mapper.EnumMapper;
import com.asianfo.springsamples.freemarker.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnumServiceImpl implements EnumService {
    @Autowired
    private EnumMapper enumMapper;
//    @Cacheable(cacheManager = "concurrentMapCacheManager",value = "tm_enum")
    @Cacheable(cacheManager = "redisCacheManager",value = "tm_enum",key="'enumpo_'+#enumCode")
    public List<TmEnum> getEnum(String enumCode){
        return enumMapper.getEnum(enumCode);
    }
}
