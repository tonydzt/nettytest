package spi.dubbo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import spi.Log;

/**
 * @author douzhitong
 * @date 2021/4/27
 */
public class DubboSpiTest {
    public static void main(String[] args) {
        ExtensionLoader<Log> extensionLoader = ExtensionLoader.getExtensionLoader(Log.class);

//        // 测试SPI
        extensionLoader.getExtension("tony").info();
//
//        // 测试Adaptive
//        extensionLoader.getAdaptiveExtension().adaptiveInfo(URL.valueOf("http://www.baidu.com?target=tony&get=susan"));
//        extensionLoader.getAdaptiveExtension().adaptiveInfo(URL.valueOf("http://www.baidu.com?get=susan"));
//        extensionLoader.getAdaptiveExtension().adaptiveInfo(URL.valueOf("http://www.baidu.com"));

        // 测试Activate
        // 不加group限制，只通过value过滤。value=log
//        extensionLoader.getActivateExtension(URL.valueOf("http://www.baidu.com?log=x"), "hahaha", null).forEach(Log::info);

//        // 只有group条件。group=consumer
//        extensionLoader.getActivateExtension(URL.valueOf("http://www.baidu.com"), "hahaha", "consumer").forEach(Log::info);
//
//        // value\group双过滤。group=consumer
//        extensionLoader.getActivateExtension(URL.valueOf("http://www.baidu.com?log=x"), "hahaha", "consumer").forEach(Log::info);
//
//        // key指定，外加group\value
//        extensionLoader.getActivateExtension(URL.valueOf("http://www.baidu.com?hahaha=tony&test1=11"), "hahaha", "consumer").forEach(Log::info);
//
//        // default前的value排在最前面，default只是起到一个变更排序的作用
//        extensionLoader.getActivateExtension(URL.valueOf("http://www.baidu.com?hahaha=tony,default,john&test1=11"), "hahaha", "consumer").forEach(Log::info);
    }
}
