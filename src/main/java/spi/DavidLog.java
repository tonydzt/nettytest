package spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;

/**
 * @author douzhitong
 * @date 2021/4/27
 */
@Activate(group = "provider", value = "log")
public class DavidLog implements Log {

    @Override
    public void info() {
        System.out.println("Hello david!");
    }

    @Override
    public void adaptiveInfo(URL url) {
        System.out.println("Hello adaptive david!");
    }
}
