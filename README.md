# flume-cacheable-interceptor-skeleton

This project provides a skeleton for a cacheable Flume interceptor. It's implemented
using the Spring Framework version 4.1.5 that provides the cache service.

To implement your own interceptor, clone this project and implement the missing part of
CacheableInterceptor and FlumeCacheService. Make sure that the data to be cached is
 comparable, i.e. has valid toString() and hashCode() methods.

Cache configuration is located at [src/main/resources/ehcache.xml]. See the
(Ehcache configuration guide)[http://ehcache.org/generated/2.9.0/html/ehc-all/#page/Ehcache_Documentation_Set%2F_ehcache_all.1.121.html%23]
for details.

