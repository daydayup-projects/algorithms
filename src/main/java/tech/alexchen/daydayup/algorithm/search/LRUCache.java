package tech.alexchen.daydayup.algorithm.search;

import java.util.LinkedHashMap;

/**
 * @author alexchen
 */
public class LRUCache {

    int capacity;
    LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
            return;
        }
        if (cache.size() >= this.capacity) {
            // 删除链表头部的 key
            cache.remove(cache.keySet().iterator().next());
        }
    }

    /**
     * 将 key 变为最近使用
     *
     */
    private void makeRecently(int key) {
        int val = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, val);
    }

}
