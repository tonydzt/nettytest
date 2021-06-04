package spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;
import spi.Log;

/**
 * @author douzhitong
 * @date 2021/4/27
 */
@Activate(group = "consumer", value = {"test", "test1"})
public class SusanLog implements Log {

    @Override
    public void info() {
        System.out.println("Hello susan!");
    }

    @Override
    public void adaptiveInfo(URL url) {
        System.out.println("Hello adaptive susan!");
    }
}
