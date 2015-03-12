package org.apache.flume.interceptor.service;

import org.junit.Assert;
import org.junit.Test;

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
}
