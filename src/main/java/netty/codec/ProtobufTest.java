package netty.codec;

import io.netty.handler.codec.protobuf.ProtobufDecoder;
import protobuf.AddPerson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author douzhitong
 * @date 2020/8/11
 */
public class ProtobufTest {

    public static void main(String[] args) {

    }

    public static void testCodec() {
        List<Object> result = new ArrayList<>();
        try {
//            ProtobufEncoder protobufEncoder = new ProtobufEncoder();
            ProtobufDecoder protobufDecoder = new ProtobufDecoder(AddPerson.PromptForAddress(new BufferedReader(new InputStreamReader(System.in)), System.out));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
