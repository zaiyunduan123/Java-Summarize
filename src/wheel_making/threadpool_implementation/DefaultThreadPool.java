package wheel_making.threadpool_implementation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jiangyunxiong on 2018/6/8.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 线程池维护工作者线程的最大数量
    private static final int MAX_WORKER_NUM = 10;
    // 线程池维护工作者线程的默认值
    private static final int DEFAULT_WORKER_NUM = 5;
    // 线程池维护工作者线程的最小数量
    private static final int MIN_WORKER_NUM = 1;
    // 使用LinkedList维护一个工作列表,里面加入客户端发起的工作
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    //工作者线程的列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    // 工作者线程的数量
    private int workerNum;
    //每个工作者线程编号生成
    private AtomicLong threadNum = new AtomicLong();


    public DefaultThreadPool() {
        this.workerNum = DEFAULT_WORKER_NUM;
        initializeWorkers(this.workerNum);
    }

    public DefaultThreadPool(int num) {
        //如果自定义线程数大于最大线程数，就设置为默认线程数
        if (num > MAX_WORKER_NUM) {
            this.workerNum = DEFAULT_WORKER_NUM;
        } else {
            this.workerNum = num;
        }
        initializeWorkers(this.workerNum);
    }

    /**
     * 初始化每个工作者线程
     * <p>
     * 1、根据线程数初始化每个工作者线程，加入工作者线程列表
     * 2、启动工作者线程
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            //添加到工作者线程的列表
            workers.add(worker);
            //启动工作者线程
            Thread thread = new Thread(worker);
            thread.start();
        }
    }

    /**
     * 把任务加入任务队列中，并通知任务该执行
     *
     * @param job
     */
    @Override
    public void execute(Job job) {
        if (job != null) {
            //根据线程的等待/通知 这里需要对jobs加锁
            synchronized (jobs) {
                jobs.addLast(job);//追加任务
                jobs.notifyAll();//提醒任务该执行
            }
        }

    }

    //全部关闭工作
    @Override
    public void shutdown() {
        for (Worker w : workers) {
            w.shutdown();
        }
    }

    /**
     * 增加工作者线程
     * <p>
     * 1、如果加入工作线程数+当前工作线程数大于最大线程数，就设置加入工作线程数等于最大-当前
     * 2、初始化工作者线程
     */

    @Override
    public void addWorkers(int num) {
        //加速，防止该线程还没增加完成而下一个线程继续增加导致工作者线程超过最大值
        synchronized (jobs) {
            if (num + this.workerNum > MAX_WORKER_NUM) {
                num = MAX_WORKER_NUM - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    /**
     * 减少工作者线程
     * <p>
     * 1、先判断需要删除的工作线程数是否大于当前工作线程数，大于就抛异常
     * 2、然后从工作者列表从头关闭线程并移除
     * 3、最后，当前工作线程数-已删除的线程数
     */

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("超过了已有的线程数量");
            }
            for (int i = 0; i < num; i++) {
                Worker worker = workers.get(i);
                if (worker != null) {
                    //关闭该线程并从列表移除
                    worker.shutdown();
                    workers.remove(i);
                }
            }
        }
        this.workerNum -= num;
    }

    @Override
    public int getJobSize() {
        return workers.size();
    }

    /**
     * 1、先判断该工作线程是否在运行
     * 2、再判断任务列表是否为空，为空就等待唤醒、否则从任务队列逐个取出任务，任务不为空就执行该任务
     * 3、注意加锁，保证线程安全
     */
    class Worker implements Runnable {
        //表示是否运行该worker
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    if (jobs.isEmpty()) {
                        try {
                            jobs.wait();//线程等待唤醒
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    //取出一个job
                    job = jobs.removeFirst();

                }
                if (job != null) {
                    job.run();
                }
            }
        }

        //终止该线程
        public void shutdown() {
            running = false;
        }
    }

}
