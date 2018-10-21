package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 该示例代码中设置了两个CountDownLatch，第一个endSignal用于控制让main线程（裁判员）必须等到其他线程（运动员）让CountDownLatch维护的数值N减到0为止。另一个startSignal用于让main线程对其他线程进行“发号施令”，startSignal引用的CountDownLatch初始值为1，而其他线程执行的run方法中都会先通过 startSignal.await()让这些线程都被阻塞，直到main线程通过调用startSignal.countDown();，将值N减1，CountDownLatch维护的数值N为0后，其他线程才能往下执行，并且，每个线程执行的run方法中都会通过endSignal.countDown();对endSignal维护的数值进行减一，由于往线程池提交了6个任务，会被减6次，所以endSignal维护的值最终会变为0，因此main线程在latch.await();阻塞结束，才能继续往下执行。
 */
public class CountDownLatchDemo {

    private static CountDownLatch startSignal = new CountDownLatch(1);
    // 用来表示裁判员需要维护的是6个运动员
    private static CountDownLatch endSignal = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(6);
        for (int i = 0;i < 6;i++){
            service.execute(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " 运动员等待裁判员响哨！！！");
                    startSignal.await();
                    System.out.println(Thread.currentThread().getName() + "正在全力冲刺");
                    endSignal.countDown();//使CountDownLatch初始值N减1
                    System.out.println(Thread.currentThread().getName() + "  到达终点");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        System.out.println("裁判员发号施令啦！！！");
        startSignal.countDown();
        endSignal.await();//调用该方法的线程等到构造方法传入的N减到0的时候，才能继续往下执行
        System.out.println("所有运动员到达终点，比赛结束！");
        service.shutdown();
    }
}
