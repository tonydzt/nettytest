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
public class ChannelTest {

    public static void main(String[] args) throws IOException {
        String path = "/Users/dzt/123.txt";
        testReadFile(path);
    }

    public static void testReadFile(String path) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile(path, "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(128);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print(buf.getChar());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }

}