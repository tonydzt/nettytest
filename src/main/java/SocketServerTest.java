import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author douzhitong
 * @date 2021/5/14
 */
public class SocketServerTest {

    public static final int PORT = 64872;//监听的端口号

    public static void main(String[] args) {
        System.out.println("服务器启动...\n");
        SocketServerTest server = new SocketServerTest();
        server.init();
    }

    public void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
//            serverSocket.setSoTimeout(1000000);
            while (true) {
                // 一旦有堵塞, 则表示服务器与客户端获得了连接
                Socket client = serverSocket.accept();
                // 处理这次连接
                new HandlerThread(client);
            }
        } catch (Exception e) {
            System.out.println("服务器异常: " + e.getMessage());
        }
    }

    private class HandlerThread implements Runnable {
        private Socket socket;

        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // 读取客户端数据
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    // 发送键盘输入的一行
                    String s = "test";
                    out.writeUTF(s);
//                    out.flush();

                    Thread.sleep(10000);
//                    out.close();
                }
            } catch (Exception e) {
                System.out.println(System.currentTimeMillis() + "服务器 run 异常: " + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }
}
