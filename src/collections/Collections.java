package 集合类;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Collections {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i<10;i++){
            Random random = new Random();
          set.add(random.nextInt(1000));
        }

        System.out.println(set);
    }
}
