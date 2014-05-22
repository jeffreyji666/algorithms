package org.jeffreyji.algorithms.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: wgji
 * @date：2014年5月22日 下午3:00:41
 * @comment:
 */

public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(1);
        cache.set(2, 1);
        System.out.println(cache.get(2));
        cache.set(3, 2);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
    }

    private Map<Integer, Integer> cache = null;

    public LRUCache(final int capacity) {
        assert (capacity > 0);
        cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            private static final long serialVersionUID = 7058487089540348148L;

            @Override
            public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        Integer val = cache.get(key);
        return val == null ? -1 : val;
    }

    public void set(int key, int value) {
        cache.put(key, value);
    }
}
