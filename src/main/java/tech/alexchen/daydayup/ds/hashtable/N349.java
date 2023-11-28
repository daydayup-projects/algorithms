package tech.alexchen.daydayup.ds.hashtable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author alexchen
 */
public class N349 {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet();
        set.stream().mapToInt(x -> x).toArray();

        int i = 1;
        Map<Integer, Integer> count = new HashMap(16);
        count.getOrDefault(i, 0);
    }
}
