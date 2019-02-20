package algorithms;


/**
 * @Auther: Jesper
 * @Date: 2019/2/12 17:23
 * @Description: 全排列
 */
public class Permutation {

    int count = 0;

    public void permutation(char[] cs, int index) {
        if (index > cs.length) {
            return;
        }

        if (index == cs.length) {
            System.out.println(new String(cs));
            count++;
        }

        for (int i = index; i < cs.length; i++) {
            swap(cs, index, i);
            permutation(cs, index + 1);
            // 再次交换，保持原状
            swap(cs, index, i);
        }
    }

    private void swap(char[] cs, int index, int target) {
        char tmp = cs[index];
        cs[index] = cs[target];
        cs[target] = tmp;
    }

    public static void main(String[] args) {
        char[] cs = new char[]{'a', 'b', 'c', 'd'};
        Permutation per = new Permutation();
        per.permutation(cs, 0);
        System.out.println("方法总数:" + per.count);
    }
}
