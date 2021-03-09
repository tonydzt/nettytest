package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import protobuf.AddPerson;
import protobuf.AddressBookProtos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

/**
 * @author dzt
 * @date 18/5/26
 * Hope you know what you have done
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //引导
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("MessageLengthEncoder", new ProtobufVarint32LengthFieldPrepender());
                            pipeline.addLast("MessageEncoder", new ProtobufEncoder());
                            pipeline.addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
//            sendFromSystemIn(f);
//            f.channel().closeFuture().sync();
            send50(f);
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private void sendFromSystemIn(ChannelFuture f) throws IOException {
        while (true) {
            f.channel().writeAndFlush(AddPerson.PromptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));
        }
    }

    private void send50(ChannelFuture f) throws IOException {
        for (int i = 0; i < 50; i++) {
            AddressBookProtos.Person.Builder person = AddressBookProtos.Person.newBuilder();
            person.setId(i);
            person.setName("tony");
            person.setEmail("email");
            AddressBookProtos.Person.PhoneNumber.Builder phoneNumber = AddressBookProtos.Person.PhoneNumber.newBuilder().setNumber("123456789");
            phoneNumber.setType(AddressBookProtos.Person.PhoneType.MOBILE);
            person.addPhones(phoneNumber);
            f.channel().writeAndFlush(person.build());
        }
    }

    public static void main(String[] args) throws Exception {
//        if (args.length != 2) {
//            System.err.println(
//                    "Usage: " + EchoClient.class.getSimpleName() +
//                    " <host> <port>");
//            return;
//        }
//
//        String host = args[0];
//        int port = Integer.parseInt(args[1]);

        String host = "127.0.0.1";
        int port = 9798;
        new EchoClient(host, port).start();
    }
}
