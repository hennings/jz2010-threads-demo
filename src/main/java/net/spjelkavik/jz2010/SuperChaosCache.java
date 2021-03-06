package net.spjelkavik.jz2010;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class SuperChaosCache implements SuperCache{
    static Map<String,Integer> cache = new HashMap<String,Integer>();

    public void add(String key, Integer value) {
        cache.put(key,value);
    }

    public Integer get(String key) {
        return cache.get(key);
    }


    public void remove(String s) {
        cache.remove(s);
    }
}
