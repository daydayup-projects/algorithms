package tech.alexchen.daydayup.ds.array;

import cn.hutool.core.util.RandomUtil;

import java.util.*;

/**
 * @author AlexChen
 */
public class ArrayListDemo {

    public static void main(String[] args) {
        iteratorTest();
//        arrayTypeTest();
    }

    public static void quoteTest() {
        Object[] src = new Object[10];
        for (int i = 0; i < 10; i++) {
            src[i] = i;
        }
        Object[] a = src;
        // 修改 a[0] 的值，src[0] 也会改变
        a[0] = -1;
        System.out.println(src[0]);

        Object[] b = Arrays.copyOf(src, 10);
        // 通过 Arrays.copyOf 拷贝，得到的是一个新数组，地址不同
        b[0] = 100;
        System.out.println(src[0]);

        // src 和 a 为同一个对象，b 为另一个对象
        System.out.println(src);
        System.out.println(a);
        System.out.println(b);
    }

    public static void iteratorTest() {
        // 准备数据
        int capacity = 5;
        ArrayList<String> list = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            list.add(RandomUtil.randomString(5));
        }
        list.add(RandomUtil.randomString(5));

        // 迭代器遍历
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.print(next + " ");
        }

    }

    public static void arrayTypeTest() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        // JDK8 返回 Integer[] 数组，JDK9+ 返回 Object[] 数组。
        Object[] array = list.toArray();
        System.out.println("array className ：" + array.getClass().getSimpleName());

        // 此处，在 JDK8 和 JDK9+ 表现不同，前者会报 ArrayStoreException 异常，后者不会。
        array[0] = new Object();

        list.toArray(new Integer[]{});
    }

}
