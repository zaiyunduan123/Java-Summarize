package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jiangyunxiong on 2018/7/17.
 */
public class ArrayListRemoveTest {

    public  void test1(){
        List<Integer> list1 = new ArrayList<>();

        for (int i = 0;i< 10;i++){
            list1.add(i);
        }

        Iterator<Integer> iter = list1.iterator();
        while(iter.hasNext()){
            Integer s = iter.next();
            if(s.equals("1")){
                iter.remove();
            }
        }
        for (int i = 0;i< 10;i++){
            System.out.println(list1.get(i));
        }
        for (int i = 0;i< list1.size();i++){
            list1.remove(i);
        }

        for (int i =0 ;i<10;i++){
            System.out.println(list1.get(i));
        }
    }

    public static void main(String[] args) {
         ArrayListRemoveTest test = new ArrayListRemoveTest();
         test.test1();
    }
}
