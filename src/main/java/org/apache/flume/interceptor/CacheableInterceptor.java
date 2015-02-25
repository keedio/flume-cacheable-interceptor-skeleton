package org.apache.flume.interceptor;

import java.util.LinkedList;
import java.util.List;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.service.FlumeCacheService;
import org.apache.flume.interceptor.service.ICacheService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/** *
 * Implements a cacheable interceptor
 * In order to cache events, the Event implementation needs to provide
 * implementation for the following methods:
 *      hashCode()
 *      equals()
 * If Event doesn't have a natural key nor implement these methods then
 * a different key generator is needed, i.e. an implementation for
 * {@link org.springframework.cache.interceptor.KeyGenerator} needs to be provided.
 *
 * @see <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/cache.html">Spring Cache docs</a>
 *
 */

public class CacheableInterceptor implements Interceptor {
    private ApplicationContext context;
    private ICacheService<Event> service;

	public CacheableInterceptor() {
        // Get Spring context
        this.context = new ClassPathXmlApplicationContext("beans.xml");
	}

	@Override
	public void initialize() {
        // Get cache service instance
        service = context.getBean( ICacheService.class );
    }

	@Override
	public Event intercept(Event event) {
        // Don't change this method body !!!!
        // Edit the FlumeCacheService.intercept method to implement your interceptor
        return service.intercept(event);
	}

	@Override
    public List<Event> intercept(List<Event> events) {
        List<Event> intercepted = new LinkedList<Event>();
        for (Event e : events) {
            intercepted.add(intercept(e));
        }
        return intercepted;
	}

    ApplicationContext getContext() {
        return context;
    }

    ICacheService<Event> getService() {
        return service;
    }

    @Override
	public void close() {
	}

	public static class Builder implements Interceptor.Builder {
		public Interceptor build() {
            return new CacheableInterceptor();
		}

		@Override
		public void configure(Context context) {
		}
	}
}