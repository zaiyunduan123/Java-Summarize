package wheel_making.threadpool_implementation;

public class Test {

    public static void main(String[] args) {
        DefaultThreadPool pool = new DefaultThreadPool();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i * 3);
                }
            }
        });
    }
}
