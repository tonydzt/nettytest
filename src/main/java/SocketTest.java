import java.io.*;
import java.net.Socket;

/**
 * @author douzhitong
 * @date 2021/5/14
 */
public class SocketTest {
    public static final String IP_ADDR = "localhost";//服务器地址
//    public static final String IP_ADDR = "localhost";
    public static final int PORT = 64872;//服务器端口号

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket(IP_ADDR, PORT);
            socket.setKeepAlive(true);
//            socket.setSoTimeout(1000000);

            while (true) {
                //读取服务器端数据
                DataInputStream input = new DataInputStream(socket.getInputStream());
                //向服务器端发送数据
//                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
//                System.out.print("请输入: \t");
//                String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
//                out.writeUTF(str);

                String ret = input.readUTF();
                System.out.println("服务器端返回过来的是: " + ret);
                // 如接收到 "OK" 则断开连接
//                if ("OK".equals(ret)) {
//                    System.out.println("客户端将关闭连接");
//                    Thread.sleep(500);
//                    break;
//                }
//
//                out.close();
//                input.close();
            }
        } catch (Exception e) {
            System.out.println("客户端异常:" + e.getMessage());
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println("客户端 finally 异常:" + e.getMessage());
                }
            }
        }
    }
}
