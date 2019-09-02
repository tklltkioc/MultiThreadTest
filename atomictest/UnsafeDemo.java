package MultiThreadTest.atomictest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author tktktkl@foxmail.com
 * @date 2019/6/26 15:13
 */
public class UnsafeDemo {
    public static void main (String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field field = Unsafe.class.getDeclaredField ("theUnsafe");
        field.setAccessible (true);
        Unsafe unsafe = (Unsafe) field.get (null);
        System.out.println (unsafe);

        //通过allocateInstance直接创建对象
        User user = (User) unsafe.allocateInstance (User.class);

        Class userClass = user.getClass ();
        Field name = userClass.getDeclaredField ("name");
        Field age = userClass.getDeclaredField ("age");
        Field id = userClass.getDeclaredField ("id");

        //获取实例变量name和age在对象内存中的偏移量并设置值
        unsafe.putInt (user, unsafe.objectFieldOffset (age), 18);
        unsafe.putObject (user, unsafe.objectFieldOffset (name), "android TV");

        // 这里返回 User.class，
        Object staticBase = unsafe.staticFieldBase (id);
        System.out.println ("staticBase:" + staticBase);

        //获取静态变量id的偏移量staticOffset
        long staticOffset = unsafe.staticFieldOffset (userClass.getDeclaredField ("id"));
        //获取静态变量的值
        System.out.println ("设置前的ID:" + unsafe.getObject (staticBase, staticOffset));
        //设置值
        unsafe.putObject (staticBase, staticOffset, "SSSSSSSS");
        //获取静态变量的值
        System.out.println ("设置前的ID:" + unsafe.getObject (staticBase, staticOffset));
        //输出USER
        System.out.println ("输出USER:" + user.toString ());

        long data = 1000;
        byte size = 2;//单位字节

        //调用allocateMemory分配内存,并获取内存地址memoryAddress
        long memoryAddress = unsafe.allocateMemory (size);
        //直接往内存写入数据
        unsafe.putAddress (memoryAddress, data);
        //获取指定内存地址的数据
        long addrData = unsafe.getAddress (memoryAddress);
        System.out.println ("addrData:" + addrData);
    }
}

class User {
    public User () {
        System.out.println ("user 构造方法被调用");
    }

    private String name;
    private int age;
    private static String id = "USER_ID";

    @Override
    public String toString () {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age + '\'' +
                ", id=" + id + '\'' +
                '}';
    }
}