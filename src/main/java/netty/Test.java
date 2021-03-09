package netty;

/**
 * @author dzt
 * @date 18/5/26
 * Hope you know what you have done
 */
public class Test {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Test test = new Test();
        Test test1 = new Test();
        System.out.println(test.getClass().isInstance(test));
        System.out.println(test.getClass().isInstance(test1));
    }
}
