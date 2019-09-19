package MultiThreadTest.atomictest;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/26 16:04
 */
public class AtomicReferenceDemo2 {

    public static AtomicReference<User> atomicUserRef = new AtomicReference<User> ();

    public static void main (String[] args) {
        User user = new User ("ss", 18);
        atomicUserRef.set (user);
        System.out.println (atomicUserRef.get ().toString ());
        User updateUser = new User ("sd5a", 25);
        atomicUserRef.compareAndSet (user, updateUser);
        // 执行结果:User{name='sd5a', age=25}
        System.out.println (atomicUserRef.get ().toString ());
    }

    static class User {
        public String name;
        private int age;

        public User (String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName () {
            return name;
        }

        @Override
        public String toString () {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
