package org.apache.flume.interceptor.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.cache.interceptor.SimpleKey;

import java.util.HashMap;
import java.util.Map;

public class MapKeyGeneratorTest {

    @Test
    public void testGenerateKey() {

        String letters = "ABCDEF";
        Map<String, String> m1 = new HashMap<String, String>();
        Map<String, String> m2 = new HashMap<String, String>();

        for (int i = 0; i < 5; i++) {
            String s = letters.substring(i, i + 1);
            m1.put(s, "Letter is " + s);
            m2.put(s, "Letter is " + s);
        }
        MapKeyGenerator generator = new MapKeyGenerator();

        Object key1 = generator.generate(null, null, m1);
        Object key2 = generator.generate(null, null, m2);

        Assert.assertEquals(key1, key2);

    }

    @Test
    public void testEmptyKey() {
        MapKeyGenerator generator = new MapKeyGenerator();
        Map[] emptyArray = new Map[0];

        Object key1 = generator.generate(null, null, emptyArray);

        Assert.assertEquals(key1, SimpleKey.EMPTY);
    }
}
