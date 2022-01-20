package com.asianfo.springsamples.freemarker.aop;

import com.alibaba.fastjson.JSONObject;
import com.asianfo.springsamples.freemarker.PageCache;
import com.asianfo.springsamples.freemarker.keyGenerator.KeyGenerator;
import com.asianfo.springsamples.freemarker.pageSelector.PageIfNeedCache;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.StringWriter;
import java.lang.reflect.Method;

@Aspect
@Component
public class ProductCacheAspect implements ApplicationContextAware {
    @Autowired
    private Configuration configuration;
    @Autowired
    private RedisTemplate redisTemplate;
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.asianfo.springsamples.freemarker.PageCache)")
    public void pointCut() {
    }

    // 使用key去寻找特定的html页面，直接返回给浏览器。
    // 当然，也可以只缓存当前页面的数据，比如372189号产品初始化需要的初始化数据，缓存起来，然后每次来的时候都使用Freemarker引擎去处理，生成html，打印回前台。
    // 两种方式都可以，第一种方式会多占用一点redis内存，第二种方式需要多占用CPU，毕竟，引擎每个页面都需要去生成html
    // 相对来说，推荐使用第一种，并且只缓存特殊产商品信息，这样保证，html存放不会太多，同时，又可以保证响应速度。
    // tips：极限情况下，比如12306，我们也可以使用nginx+lua的方式来实现这个缓存，这样的话，就省去了nginx到服务端的网络传输，直接使用nginx去连接redis，速度更快，但是lua还是需要学习一下的。（OpenResty）
    // 当然，很多时候，我们依然可以使用gateWay，不需要使用nginx，直接使用的话，用java来写，就也还行。
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) {
        boolean needCache = true;
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Object[] args = point.getArgs();
        PageCache pageCache = method.getAnnotation(PageCache.class);
        String html = "";
        // 这里添加判断，判断当前key，是否需要缓存，毕竟我们重点关注的页面可能随时会变
        // 选择器，判断是否需要缓存，如果为空的话，就是所有都缓存
        String selector = pageCache.selector();
        if (StringUtils.hasText(selector)) {
            PageIfNeedCache pageIfNeedCache = applicationContext.getBean(selector, PageIfNeedCache.class);
            needCache = pageIfNeedCache.ifNeedCache(args);
        }
        // 判断是否需要缓存
        if (needCache) {
            String cacheKey = "";
            // 获取KeyGenerator，生成缓存Key
            // 当为空的时候，使用默认的Generator，默认使用第一个参数。
            String keyGeneratorName = pageCache.keyGenerator();
            if (StringUtils.hasText(keyGeneratorName)) {
                KeyGenerator keyGenerator = applicationContext.getBean(keyGeneratorName, KeyGenerator.class);
                cacheKey = keyGenerator.generate(args);
            } else {
                if (args != null && args.length > 0 && args[0] != null) {
                    cacheKey = args[0] + "";
                }
            }
            if(!StringUtils.hasText(cacheKey)) {
                throw new RuntimeException("没有可以使用的缓存Key，请检查参数和KeyGenerator");
            }
            // 不使用分布式锁了，没太大必要，无非是重复一下而已，不会报错。
            // 判断是否在缓存中
            boolean isCache = redisTemplate.opsForHash().hasKey(pageCache.cacheKeyPrefix(), cacheKey);
            if (isCache) {
                Object o = redisTemplate.opsForHash().get(pageCache.cacheKeyPrefix(), cacheKey);
                if (o != null) {
                    return o.toString();
                }
            } else {
                try {
                    html = getHtml(point, args, pageCache);
                    if (needCache) {
                        redisTemplate.opsForHash().put(pageCache.cacheKeyPrefix(), cacheKey, html);
                    }
                    return html;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    // 在这里处理没找到页面的异常，可以给指向某个特定页面。
                }
            }
        } else {
            try {
                return getHtml(point, args, pageCache);
            } catch (Throwable e) {
                e.printStackTrace();
                // 在这里处理没找到页面的异常，可以给指向某个特定页面。
            }
        }
        return null;
    }

    private String getHtml(ProceedingJoinPoint point, Object[] args, PageCache cache) throws Throwable {
        String html = "";
        Object proceedResult = point.proceed(args);
        // 根据路径获取Freemarker的Template
        Template template = configuration.getTemplate(cache.filePath());
        StringWriter stringWriter = new StringWriter();
        if (proceedResult != null) {
            JSONObject param = JSONObject.parseObject(proceedResult.toString());
            // 将template的输出，打印到outbuffer里。这个在springmvc篇里有介绍。
            template.process(param, stringWriter);
            html = stringWriter.toString();
        }
        return html;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
