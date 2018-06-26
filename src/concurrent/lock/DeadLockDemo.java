package concurrent.lock;

/**
 * 一个死锁例子
 * <p>
 * 通过flag区别线程A和B、线程A获得AAA对象的锁，继续执行，此时如果能拿到对象BBB的锁，run（）执行完毕，
 * 线程1就可以顺利结束，然后释放对两个对象的锁现在的问题是，如果在线程A获得对象BBB的锁之前，线程B已经获得对象BBB的锁，那么线程B
 * 就会寻求对象AAA的锁，显然是无法得到的。这就导致两个线程都无法释放自己的锁。
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        LockDemo A = new LockDemo(true);
        LockDemo B = new LockDemo(false);
        new Thread(A).start();
        new Thread(B).start();
    }
}

class LockDemo implements Runnable {

    public static final Object AAA = new Object();
    public static final Object BBB = new Object();
    boolean flag;

    public LockDemo(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (AAA) {
                System.out.println("if lock AAA");
                synchronized (BBB) {
                    System.out.println("if lock BBB");
                }
            }
        } else {
            synchronized (BBB) {
                System.out.println("else lock BBB");
                synchronized (AAA) {
                    System.out.println("else lock AAA");
                }
            }
        }
    }
}
