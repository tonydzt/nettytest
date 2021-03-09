package nio.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author dzt
 * @date 18/7/30
 * Hope you know what you have done
 */
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        String path = "/Users/dzt/123.txt";
        testReadFileDecode(path);
    }

    public static void testReadFileDecode(String path) throws IOException {

        RandomAccessFile fis = new RandomAccessFile(path, "rw");
        FileChannel channel = fis.getChannel();
        channel.truncate(4);

        System.out.println("File Size: " + channel.size());
        System.out.println("Position: " + channel.position());
        /*
         * Java.nio.charset.Charset处理了字符转换问题。
         * 它通过构造CharsetEncoder和CharsetDecoder将字符序列转换成字节和逆转换。
         */
        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        ByteBuffer buffer = ByteBuffer.allocate(128);
        CharBuffer charBuffer = CharBuffer.allocate(128);
        int i = channel.read(buffer);
        while(i != -1){
            buffer.flip(); // 切换到读模式
            decoder.decode(buffer, charBuffer, false);
            charBuffer.flip(); // 切换到读模式
            while(charBuffer.hasRemaining()){
                char c = charBuffer.get();
                System.out.print(c);
            }
            charBuffer.clear();
            buffer.clear();
            i = channel.read(buffer);
        }
        channel.close();
    }
}
