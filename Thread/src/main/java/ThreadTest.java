import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * #org 北京信安世纪科技股份有限公司
 * @author jiangyan
 * @date 2021/3/31 21:30
 * @since 5.5.40.12 Patch32.1 +
 */
public class ThreadTest {

    private static ThreadLocal<Integer> threadLocal1 = ThreadLocal.withInitial(() -> 1000);

    private static ThreadLocal<String> threadLocal2 = new ThreadLocal<>();

    /**
     * 创建 Thread 的三种方式
     * @Author 江岩
     * @Date   2021/3/31 21:30
     * @Param args
     * @return void
     **/
    public static void main(String[] args) {

        ThreadLocal<Integer> threadLocal3 = ThreadLocal.withInitial(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 123;
            }
        });

        Thread thread = new Thread();

        System.out.println(threadLocal1.get());
        threadLocal1.remove();
        System.out.println(threadLocal1.get());


        threadLocal2.set("1234");

        System.out.println(threadLocal2.get());
        threadLocal2.remove();
        System.out.println(threadLocal2.get());


        System.out.println(threadLocal3.get());

        new InheritableThreadLocal<String>();



    }
    // 临时保存
    public static void main1(String[] args) {
        System.out.println("第一种创建线程的方式:集成 Thread");
        Thread1 thread1 = new Thread1();
        thread1.start();

        System.out.println("第二种创建线程的方式：实现 Runnable");
        Thread thread2 = new Thread(new Runnable1());
        thread2.start();

        System.out.println("第三种创建线程的方式：线程池");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 3; i++) {
            singleThreadExecutor.execute(new Runnable1());
        }

        System.out.println("第四种创建线程的方式：Callable");
        Callable1 callable1 = new Callable1();

        try {
            callable1.call();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // 继承 Thread 实现线程构造器
    static class Thread1 extends Thread {
        public void run() {
            System.out.println("thread1 extends Thread!!!");
        }
    }

    // 实现 Runnable 实现线程构造器
    static class Runnable1 implements Runnable {
        @Override
        public void run() {
            System.out.println("thread1 implements Runnable!!!");
        }
    }

    // 实现 Callable
    static class Callable1 implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "implements Callable";
        }
    }


}
