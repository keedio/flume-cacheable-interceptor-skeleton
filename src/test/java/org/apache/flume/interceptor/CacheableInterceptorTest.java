package org.apache.flume.interceptor;

import org.apache.flume.Event;
import org.apache.flume.interceptor.service.ICacheService;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CacheableInterceptorTest {

    @Test
    public void testSpringContext() {

        CacheableInterceptor.Builder builder = new CacheableInterceptor.Builder();
        CacheableInterceptor interceptor = (CacheableInterceptor) builder.build();

        ApplicationContext context = interceptor.getContext();
        Assert.assertNotNull(context);

        String id = context.getId();
        Assert.assertNotNull(id);

    }

    @Test
    public void testSpringService() {

        CacheableInterceptor.Builder builder = new CacheableInterceptor.Builder();
        CacheableInterceptor interceptor = (CacheableInterceptor) builder.build();
        interceptor.initialize();

        ICacheService<Event> service = interceptor.getService();
        Assert.assertNotNull(service);

        String name = service.getClass().getCanonicalName();
        Assert.assertNotNull(name);

    }
}
