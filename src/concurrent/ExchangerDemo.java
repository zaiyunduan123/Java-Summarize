package concurrent;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 当两个线程都到达调用exchange方法的同步点的时候，两个线程就能交换彼此的数据。
 */
public class ExchangerDemo {

    private static Exchanger<String> exchanger = new Exchanger();

    public static void main(String[] args) {

        //代表男生和女生
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(() -> {
            try {
                //男生对女生说的话
                String girl = exchanger.exchange("我其实暗恋你很久了......");
                System.out.println("女孩儿说：" + girl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                System.out.println("女生慢慢的从教室你走出来......");
                TimeUnit.SECONDS.sleep(3);
                //男生对女生说的话
                String boy = exchanger.exchange("我也很喜欢你......");
                System.out.println("男孩儿说：" + boy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}
