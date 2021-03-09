package reflect.method;

import java.util.stream.Stream;

/**
 * @author douzhitong
 * @date 2019/11/15
 */
public class MethodTest {

    public static void main(String[] args) {
//        System.out.println(MethodTest.class);
        Stream.of(MethodTest.class.getMethods()).forEach(a -> System.out.println(a.toString()));
//        System.out.println(MethodTest.class.getMethods()[1]);
//        Stream.of(MethodTest.class.getMethods()[0]).forEach(a -> System.out.println(a.toString()));
    }
}
