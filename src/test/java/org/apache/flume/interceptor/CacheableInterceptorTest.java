package org.apache.flume.interceptor;

import org.apache.flume.Event;
import org.apache.flume.interceptor.service.ICacheService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;


public class CacheableInterceptorTest {

    @Test
    public void testSpringContext() {

        CacheableInterceptor.CacheableBuilder cacheableBuilder = new CacheableInterceptor.CacheableBuilder();
        CacheableInterceptor interceptor = (CacheableInterceptor) cacheableBuilder.build();

        ApplicationContext context = interceptor.getContext();
        Assert.assertNotNull(context);

        String id = context.getId();
        Assert.assertNotNull(id);

    }

    @Test
    public void testSpringService() {

        CacheableInterceptor.CacheableBuilder cacheableBuilder = new CacheableInterceptor.CacheableBuilder();
        CacheableInterceptor interceptor = (CacheableInterceptor) cacheableBuilder.build();
        interceptor.initialize();

        ICacheService<Event> service = interceptor.getService();
        Assert.assertNotNull(service);

        String name = service.getClass().getCanonicalName();
        Assert.assertNotNull(name);

    }
}
