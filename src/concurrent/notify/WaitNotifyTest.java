package concurrent.notify;

/**
 * @Auther: Jesper
 * @Date: 2019/1/31 10:34
 * @Description:  启动两个线程, 一个输出 1,3,5,7…99, 另一个输出 2,4,6,8…100 最后 STDOUT 中按序输出 1,2,3,4,5…100
 *
 *  用 Java 的 notify 机制实现
 */
public class WaitNotifyTest {

    private static int state = 1; // 通状态值来实现两个线程交替打印
    private static int num1 = 1;  // 线程1 要打印的数值
    private static int num2 = 2;  // 线程2 要打印的数值

    public static void main(String[] args) {
        final WaitNotifyTest t = new WaitNotifyTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num1 < 100) {
                    //两个线程都用t对象作为锁，保证每个交替期间只有一个线程在打印
                    synchronized (t) {
                        if (state != 1) {
                            try {
                                t.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + ":" + num1);
                        num1 += 2; // 因为是交替打印，所以每次打印完，值需要+2
                        // 线程1打印完成后, 将state赋值为2, 表示接下来将轮到线程2打印
                        state = 2;
                        // notify()方法唤醒在t上wait的线程2, 同时线程1将退出同步代码块, 释放t锁
                        t.notify();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (num2 < 100) {
                    //两个线程都用t对象作为锁，保证每个交替期间只有一个线程在打印
                    synchronized (t) {
                        if (state != 2) {
                            try {
                                t.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println(Thread.currentThread().getName() + ":" + num2);
                        num2 += 2;
                        state = 1;
                        t.notify();
                    }
                }
            }
        }).start();
    }
}
