package concurrent;

import java.util.concurrent.*;

/**
 * Semaphore用来做特殊资源的并发访问控制是相当合适的，如果有业务场景需要进行流量控制，可以优先考虑Semaphore。
 */
public class SemaphoreDemo {

    // ，Semaphore允许的最大许可数为5，也就是允许的最大并发执行的线程个数为5
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {

        // 表示50个学生
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i< 10;i++){
            service.execute(() ->{
                try{
                    System.out.println(Thread.currentThread().getName() + "同学准备获取笔...");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "  同学获取到笔");
                    System.out.println(Thread.currentThread().getName() + "  填写表格ing.....");
                    TimeUnit.SECONDS.sleep(3);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "  填写完表格，归还了笔！！！！！！");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }
}
