package wheel_making.threadpool_implementation;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        DefaultThreadPool excutor = new DefaultThreadPool(5);
        for (int i = 0; i < 10; i++) {
            excutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程 " + Thread.currentThread().getName() + "在帮我干活");
                }
            });
        }
//        excutor.shutdown();
    }
}
