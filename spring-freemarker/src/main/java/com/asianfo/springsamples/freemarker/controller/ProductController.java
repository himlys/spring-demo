package com.asianfo.springsamples.freemarker.controller;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.freemarker.PageCache;
import com.asianfo.springsamples.freemarker.entity.Product;
import com.asianfo.springsamples.freemarker.pageSelector.impl.ProductPageIfNeedCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("/{1}")
    @PageCache(cacheKeyPrefix = "products:pagecache",filePath = "/products/Product.ftlh",selector = "productPageIfNeedCache",keyGenerator = "productKeyGenerator")
    public @ResponseBody String aspect(@PathVariable("1") int product) {
        // 假设，这里需要做大量的初始化操作。最终得到一个页面需要的初始化数据。
        Product p = new Product();
        p.setProductId(897623);
        p.setName("1000M宽带");
        p.setPrice(1200.00);
        JSONObject o = new JSONObject();
        o.put("product",p);
        return o.toJSONString();
    }
    // 一个简单的去除缓存的函数，第一个参数是密码，第二个参数是产品ID，传0，就是所有
    @RequestMapping("/clear/{1}/{2}")
    public @ResponseBody JSONObject clear(@PathVariable("1") String pass,@PathVariable("2") int product) {
        if("fjKJ*&2LK".equals(pass)) {
            if(0 == product) {
                redisTemplate.delete("products:pagecache");
            }else {
                redisTemplate.opsForHash().delete("products:pagecache","FreeMarker_Cache" + product);
            }
        }
        JSONObject o = new JSONObject();
        o.put("code","0000");
        return o;
    }
}
