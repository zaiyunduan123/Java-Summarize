package collections;

import java.math.BigDecimal;

/**
 * Created by jiangyunxiong on 2018/7/17.
 */
public class FoaltTest {
    public static void main(String[] args) {
        double a=0.2;
        double b=0.4;
        System.out.println("正常的" + String.valueOf(a+b));//输出了 0.6000000000000001
        BigDecimal a1=new BigDecimal("0.2");
        BigDecimal a2=new BigDecimal("0.4");
        System.out.println("解决后的" + String.valueOf(a1.add(a2)));//输出了 0.6
    }
}
