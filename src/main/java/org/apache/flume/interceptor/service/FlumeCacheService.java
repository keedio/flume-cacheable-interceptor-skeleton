package org.apache.flume.interceptor.service;

import org.apache.flume.Event;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class FlumeCacheService implements ICacheService<Event> {

    @Override
    @Cacheable(value = "FlumeCachedEvent")
    public Event intercept(Event d) {
        // TODO: implement!
        return d;
    }
}
