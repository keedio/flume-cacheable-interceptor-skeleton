package org.apache.flume.interceptor.service;

public interface ICacheService<T> {
    public T intercept(T d);
    public boolean evictCache();
}
