package jdk;

import java.util.*;

import static java.lang.Runtime.getRuntime;

/**
 * Created by jiangyunxiong on 2018/4/30.
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> synList = Collections.synchronizedList(list);

        synchronized (list) {

            Iterator<Integer> iterator = synList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
        int[] i = {1, 1, 1, 1};
        Arrays.sort(i);
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });//底层调用Arrays,sort();


        int i1 = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu:" + i1);
    }

}
