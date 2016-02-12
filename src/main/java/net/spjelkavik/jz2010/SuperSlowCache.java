package net.spjelkavik.jz2010;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;


@Component
public class SuperSlowCache implements SuperCache {
    static Map<String,Integer> cache = new Hashtable<String,Integer>();

    @Override
    public void add(String key, Integer value) {
        cache.put(key,value);
    }

    @Override
    public Integer get(String key) {
        return cache.get(key);
    }


    @Override
    public void remove(String s) {
        cache.remove(s);
    }
}
