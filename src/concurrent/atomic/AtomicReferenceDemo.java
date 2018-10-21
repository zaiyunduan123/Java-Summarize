package concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    private static AtomicReference<User> reference = new AtomicReference<>();

    private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");



    public static void main(String[] args) {
        User user1 = new User("a", 1);
        reference.set(user1);
        User user2 = new User("b", 2);
        User user = reference.getAndSet(user2);
        System.out.println(user);
        System.out.println(reference.get());


        int oldValue = updater.getAndAdd(user1, 5);
        System.out.println(oldValue);
        System.out.println(updater.get(user1));

    }




    static class User {
        private String userName;
        public volatile int age;

        public User(String userName, int age) {
            this.userName = userName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userName='" + userName + '\'' +
                    ", age=" + age +
                    '}';
        }
    }



}

