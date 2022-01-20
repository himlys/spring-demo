package com.asianfo.springsamples.freemarker.pageSelector.impl;

import com.asianfo.springsamples.freemarker.entity.TmEnum;
import com.asianfo.springsamples.freemarker.pageSelector.PageIfNeedCache;
import com.asianfo.springsamples.freemarker.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("productPageIfNeedCache")
public class ProductPageIfNeedCache implements PageIfNeedCache {
    @Autowired
    private EnumService enumService;
    @Override
    public boolean ifNeedCache(Object[] args) {
        List<TmEnum> enums = enumService.getEnum("product_page_cache");
        String product_id = (int)args[0] + "";
        return enums.stream().filter(tmEnum -> tmEnum.getEnumFieldCode() != null && tmEnum.getEnumFieldCode().equals(product_id)).count() > 0;
    }
}
