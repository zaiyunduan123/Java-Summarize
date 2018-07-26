package jdk;

public class FinalExample {
    int i;// 普通变量
    final int j;// final 变量
    static FinalExample obj;

    public FinalExample() {
        i = 1;// 写普通域
        j = 2;// 写 final 域
    }

    public static void writer() {// 写线程 A 执行
        obj = new FinalExample();
    }

    public static void reader() {// 读线程 B 执行
        FinalExample object = obj;
        int a = object.i;

        int b = object.j;
    }
}
