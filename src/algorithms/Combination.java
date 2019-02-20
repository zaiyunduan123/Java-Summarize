package algorithms;

/**
 * @Auther: Jesper
 * @Date: 2019/2/12 17:41
 * @Description: 全组合
 */
public class Combination {
    public void combination(String s) {
        char[] strs = s.toCharArray();
        int n = s.length();
        int nbit = 1 << n;

        for (int i = 0; i < nbit; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = 1 << j;
                if ((tmp & i) != 0) {
                    System.out.print(strs[j]);
                }
            }
            System.out.println();
        }
        System.out.println("结果数为:" + (nbit - 1));
    }

    public static void main(String[] args) {
        new Combination().combination("cgz");
    }
}
