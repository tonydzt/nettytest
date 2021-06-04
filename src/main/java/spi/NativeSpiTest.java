package spi;

import java.util.ServiceLoader;

/**
 * @author douzhitong
 * @date 2021/4/27
 */
public class NativeSpiTest {

    public static void main(String[] args) {
        ServiceLoader<Log> serviceLoader = ServiceLoader.load(Log.class);
        for (Log log : serviceLoader) {
            log.info();
        }
    }
}
