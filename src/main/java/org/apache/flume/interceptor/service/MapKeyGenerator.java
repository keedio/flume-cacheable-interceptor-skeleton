package org.apache.flume.interceptor.service;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Luca Rosellini <lrosellini@keedio.com> on 11/3/15.
 */
@Component("mapKeyGenerator")
public class MapKeyGenerator implements KeyGenerator {
    protected static final long MULTIPLIER = 31;

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return generateKey(params);
    }

    /**
     * Generate a key based on the specified parameters.
     */
    public static Object generateKey(Object... params) {
        if (params.length == 0) {
            return SimpleKey.EMPTY;
        }
        Object param = params[0];
        if (param != null && Map.class.isAssignableFrom(param.getClass())) {
            Map<String, String> innerMap = (Map<String, String>) param;

            return new SimpleKey(innerMap.entrySet().hashCode() * MULTIPLIER);
        }

        return new SimpleKey(params);
    }
}
