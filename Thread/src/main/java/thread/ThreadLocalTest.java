package thread;

/**
 * #org 北京信安世纪科技股份有限公司
 * @author jiangyan
 * @date 2021/3/31 20:40
 * @since 5.5.40.12 Patch32.1 +
 */
public class ThreadLocalTest {


    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("1111111");
            }
        });
        thread.start();
    }


}
