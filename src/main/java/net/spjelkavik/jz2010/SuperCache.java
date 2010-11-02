package net.spjelkavik.jz2010;

/**
 * User: hennings
 * Date: 07.sep.2010
 */
public interface SuperCache {
    void add(String key, Integer value);

    Integer get(String key);

    void remove(String s);
}
