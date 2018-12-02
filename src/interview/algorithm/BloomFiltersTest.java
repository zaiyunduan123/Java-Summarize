package interview.algorithm;


import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * 如何判断一个元素在亿级数据中是否存在？
 * <p>
 * 为了方便调试加入了 GC 日志的打印，以及内存溢出后 Dump 内存。
 * -Xms64m -Xmx64m -XX:+PrintHeapAtGC -XX:+HeapDumpOnOutOfMemoryError
 */
public class BloomFiltersTest {

    @Test
    public void hashMapTest() {
        long start = System.currentTimeMillis();

        HashSet<Integer> hashSet = new HashSet<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            hashSet.add(i);
        }

        Assert.assertTrue(hashSet.contains(1));
        Assert.assertTrue(hashSet.contains(2));
        Assert.assertTrue(hashSet.contains(3));

        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));

    }

    @Test
    public void bloomFilterTest() {
        long start = System.currentTimeMillis();
        BloomFilters bloomFilters = new BloomFilters(10000000);
        for (int i = 0; i < 10000000; i++) {
            bloomFilters.add(i + "");
        }
        Assert.assertTrue(bloomFilters.check(1 + ""));
        Assert.assertTrue(bloomFilters.check(2 + ""));
        Assert.assertTrue(bloomFilters.check(3 + ""));
        Assert.assertTrue(bloomFilters.check(999999 + ""));
        Assert.assertFalse(bloomFilters.check(400230340 + ""));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start));

    }

    @Test
    public void guavaTest() {
        long star = System.currentTimeMillis();
       /* BloomFilter<Integer> filter = BloomFilter.create(
                Funnels.integerFunnel(),
                10000000,
                0.01);
        for (int i = 0; i < 10000000; i++) {
            filter.put(i);
        }
        Assert.assertTrue(filter.mightContain(1));
        Assert.assertTrue(filter.mightContain(2));
        Assert.assertTrue(filter.mightContain(3));
        Assert.assertFalse(filter.mightContain(10000000));*/
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));

    }

}
