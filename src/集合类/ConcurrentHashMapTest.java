package 集合类;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
    }
}
