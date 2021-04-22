package jni;

/**
 * @author douzhitong
 * @date 2021/4/21
 */
public class HelloWorld {

    /**
     * 1.声明这是一个native函数，由本地代码实现
     */
    public static native String sayHello(String name);

    public static void main(String[] args) {
        // 3.调用本地函数jav
        String text = sayHello("测试");
        System.out.println(text);
    }

    static {
        // /Users/dzt/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.
//        System.out.println(System.getProperty("java.library.path"));
        // 2.加载实现了native函数的动态库，只需要写动态库的名字
        System.loadLibrary("HelloWorld");
    }
}
