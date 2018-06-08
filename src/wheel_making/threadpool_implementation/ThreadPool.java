package wheel_making.threadpool_implementation;

/**
 * Created by jiangyunxiong on 2018/6/8.
 */
public interface ThreadPool<Job extends Runnable> {
    //执行一个任务
    void execute(Job job);
    //关闭线程池
    void shutdown();
    //增加工作者线程，即用来执行任务的线程
    void addWorkers(int num);
    //减少工作者线程
    void removeWorker(int num);
    //获取正在等待执行的任务数量
    int getJobSize();
}
