package JDK源码分析;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

        synchronized (list){

            Iterator<Integer> iterator = synList.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next());
            }
        }
    }

}
