package com.study.kubernete.user.copy;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 对像复制，目标对源不一样，日志的复制请用LogBeanCopierUtils，优化过
 */
@Slf4j
public class BeanCopierTool {
    /**
     * the beanCopierMap
     */
    private static final CacheServiceImpl<String, ConstructorAccess> constructorAccessCache = CacheServiceImpl.builder().expireAfterAccess(1).unit(TimeUnit.HOURS).build();
    private static final CacheServiceImpl<String, BeanCopier> beanCopierCache = CacheServiceImpl.builder().expireAfterAccess(1).unit(TimeUnit.HOURS).build();

    /**
     * @param source
     * @param target
     * @return T
     * @description 两个类对象之间转换
     */
    public static <T, M> T convert(M source, Class<T> target) {
        T ret = null;
        if (source == null) {
            return null;
        }
        try {
            ret = getConstructor(target);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        BeanCopier beanCopier = getBeanCopier(source.getClass(), target);
        beanCopier.copy(source, ret, new DeepCopyConverter(target));

        return ret;
    }

    public static <T, M> List<T> convert(List<M> sources, Class<T> target) {
        List<T> rets = new ArrayList<>();
        if (sources == null) {
            return rets;
        }
        for (M source : sources) {
            T ret = convert(source, target);
            if (ret == null) {
                continue;
            }
            rets.add(ret);
        }
        return rets;
    }


    /**
     * @param source
     * @param target
     * @return BeanCopier
     * @description 获取BeanCopier
     */
    public static BeanCopier getBeanCopier(Class<?> source, Class<?> target) {
        String beanCopierKey = generateBeanKey(source, target);
        BeanCopier beanCopier = beanCopierCache.get(beanCopierKey);
        if (beanCopier != null) {
            return beanCopier;
        }
        beanCopier = BeanCopier.create(source, target, false);
        beanCopierCache.put(beanCopierKey, beanCopier);
        return beanCopierCache.get(beanCopierKey);
    }

    /**
     * @param source
     * @param target
     * @return String
     * @description 生成两个类的key
     */
    public static String generateBeanKey(Class<?> source, Class<?> target) {
        return source.getName() + "@" + target.getName();
    }

    private static <T> T getConstructor(Class<T> targetClass) {
        ConstructorAccess<T> constructorAccess = getConstructorAccess(targetClass);
        return constructorAccess.newInstance();
    }

    private static <T> ConstructorAccess<T> getConstructorAccess(Class<T> targetClass) {
        ConstructorAccess<T> constructorAccess = constructorAccessCache.get(targetClass.toString());
        if (constructorAccess != null) {
            return constructorAccess;
        }
        try {
            constructorAccess = ConstructorAccess.get(targetClass);
            constructorAccess.newInstance();
            constructorAccessCache.put(targetClass.toString(), constructorAccess);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return constructorAccess;
    }
}
