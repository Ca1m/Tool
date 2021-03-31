/**
 * #org 北京信安世纪科技股份有限公司
 * @author jiangyan
 * @date 2021/3/31 21:30
 * @since 5.5.40.12 Patch32.1 +
 */
public class ThreadTest {

    /**
     * 创建 Thread 的三种方式
     * @Author 江岩
     * @Date   2021/3/31 21:30
     * @Param args
     * @return void
     **/
    public static void main(String[] args) {

        System.out.println("第一种创建线程的方式");
        Thread1 thread1 = new Thread1();
        thread1.start();

        System.out.println("第二种创建线程的方式");
        Thread thread2 = new Thread(new Runnable1());
        thread2.start();

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




}
