package jdk;


/**
 * Created by jiangyunxiong on 2018/7/26.
 */
public class ForTest {
    public static void main(String[] args) {
        int[] array = new int[10000000];

        Long time = System.currentTimeMillis();
        for (int i:array) {
            int m = 1;
        }
        System.out.println(System.currentTimeMillis());
        time = System.currentTimeMillis();
        for (int i =0 ;i<array.length;i++) {
            int m = 1;
        }
        System.out.println(System.currentTimeMillis());
        time = System.currentTimeMillis();
        int i=0;
        while (i<array.length) {
            i++;
        }
        System.out.println(System.currentTimeMillis());
    }
}
