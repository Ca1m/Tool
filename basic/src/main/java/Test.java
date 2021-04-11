import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * todo (自定义类描述)
 *
 * @author yancy
 * @version 1.0
 * @data 2021/4/7
 */
public class Test {


    public static void main(String[] args) {

        int a = 42;
        double b = 42.00001;
        double c = 42.0;

        System.out.println(a == b);
        System.out.println(b == c);


        StringBuffer buffer = new StringBuffer();

        Vector<String> vector = new Vector();


        List<String> list = new ArrayList<String>();
        list.add("111");


    }
}
