package pool;


import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Jesper
 * @Date: 2019/1/24 10:20
 * @Description:
 */
public class ScheduledThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        //创建大小为5的线程池
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(5);

        for (int i = 0; i < 3; i++) {
            Task worker = new Task("task-" + i);
            //周期性执行，每5秒执行一次
            scheduledExecutorService.scheduleAtFixedRate(worker, 0, 5, TimeUnit.SECONDS);

        }
        Thread.sleep(10000);

        System.out.println("Shutting down executor...");

        scheduledExecutorService.shutdown();
        boolean isDone;

        do {
            isDone = scheduledExecutorService.awaitTermination(1, TimeUnit.DAYS);
            System.out.println("awaitTermination...");
        } while (!isDone);

        System.out.println("Finished all threads");
    }
}

class Task implements Runnable {

    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("name = " + name + ", startTime = " + new Date());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + name + ", endTime = " + new Date());
    }
}
