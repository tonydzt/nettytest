package spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author douzhitong
 * @date 2021/4/27
 */
@SPI("log")
public interface Log {

    void info();

    @Adaptive({"target", "get"})
    void adaptiveInfo(URL url);
}
