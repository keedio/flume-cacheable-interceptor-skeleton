package org.apache.flume.interceptor;

import org.apache.flume.Event;
import org.apache.flume.interceptor.service.ICacheService;
import org.apache.flume.interceptor.service.SimpleEvent;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.LinkedList;
import java.util.List;


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

    @Test
    public void testInterceptEvent() {
        CacheableInterceptor.CacheableBuilder cacheableBuilder = new CacheableInterceptor.CacheableBuilder();
        CacheableInterceptor interceptor = (CacheableInterceptor) cacheableBuilder.build();
        interceptor.initialize();

        Event event = new SimpleEvent(1);

        Event e1 = interceptor.intercept(event);
        Event e2 = interceptor.intercept(event);
        Assert.assertEquals(e1, e2);
    }

    @Test
    public void testInterceptEventList() {
        CacheableInterceptor.CacheableBuilder cacheableBuilder = new CacheableInterceptor.CacheableBuilder();
        CacheableInterceptor interceptor = (CacheableInterceptor) cacheableBuilder.build();
        interceptor.initialize();

        List<Event> eventList = new LinkedList();
        eventList.add(new SimpleEvent(1));
        eventList.add(new SimpleEvent(2));

        List<Event> l1 = interceptor.intercept(eventList);
        List<Event> l2 = interceptor.intercept(eventList);

        Assert.assertEquals(l1, l2);
    }
}
