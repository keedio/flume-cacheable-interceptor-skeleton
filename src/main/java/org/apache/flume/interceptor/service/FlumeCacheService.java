package org.apache.flume.interceptor.service;

import org.apache.flume.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class FlumeCacheService implements ICacheService<Event> {

    private static final Logger logger = LoggerFactory.getLogger(FlumeCacheService.class);

    @Override
    @Cacheable(value = "FlumeCachedEvent")
    public Event intercept(Event d) {
        // Cached method
        logger.info("Flume cache Service intercepting: " + d.toString());
        return d;
    }

    @Override
    @CacheEvict(value = "FlumeCachedEvent", allEntries = true, beforeInvocation = true)
    public boolean evictCache() {
        // Eviction result
        return true;
    }
}
