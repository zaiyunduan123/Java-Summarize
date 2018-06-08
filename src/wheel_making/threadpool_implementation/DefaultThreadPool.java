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

    //初始化每个工作者线程
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

    @Override
    public void execute(Job job) {
        if (job != null) {
            //根据线程的等待/通知 这里需要对jobs加锁
            synchronized (jobs) {
                jobs.addLast(job);
                job.notifyAll();
            }
        }

    }

    @Override
    public void shutdown() {
        for (Worker w : workers) {
            w.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {

    }

    @Override
    public void removeWorker(int num) {

    }

    @Override
    public int getJobSize() {
        return workers.size();
    }


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
                    if (job != null) {
                        job.run();
                    }
                }
            }
        }

        //终止该线程
        public void shutdown() {
            running = false;
        }
    }

}
