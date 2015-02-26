package org.apache.flume.interceptor.service;

import junit.framework.Assert;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.apache.flume.Event;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class FlumeCacheServiceTest {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FlumeCacheServiceTest.class);

    @Test
    public void testEventCaching() {

        try {

            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            ICacheService<Event> service = context.getBean(ICacheService.class);
            CacheManager cacheManager = context.getBean(CacheManager.class);
            Cache cache = cacheManager.getCache("FlumeCachedEvent");

            Event e1 = new SimpleEvent(1);
            Event e2 = new SimpleEvent(2);
            List<Event> events = Arrays.asList(e1, e2, e1, e2);

            List<?> beforeKeys = cache.getKeys();
            for (Event e : events) {
                logger.info("Calling intercept for event: " + e.toString());
                service.intercept(e);
            }
            List<?> afterKeys = cache.getKeys();

            Assert.assertFalse( beforeKeys.contains(e1) || beforeKeys.contains(e2) );
            Assert.assertEquals( beforeKeys.size() + 2, afterKeys.size());
            Assert.assertTrue( afterKeys.contains(e1) && afterKeys.contains(e2) );

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
