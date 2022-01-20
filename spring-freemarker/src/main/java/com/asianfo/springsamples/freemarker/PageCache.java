package com.asianfo.springsamples.freemarker;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageCache {
    // 放到Redis中的缓存的前缀
    String cacheKeyPrefix();
    // 加载的路径
    String filePath();
    // 过期时间
    int expireSeconds() default 86400;
    // 选择器，判断是否需要缓存，如果为空的话，就是所有都缓存
    String selector();
    // 注掉了，两个的话，不好弄强制使用
//    // 选择器类，判断是否需要缓存
//    Class<?>[] selectorClass() default {};
    // 当为空的时候，使用默认的Generator，默认使用第一个参数。
    String keyGenerator() default "";
}
