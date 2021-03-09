package kafka;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.JsonUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author douzhitong
 * @date 2018/11/27
 */
public class kafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(kafkaProducer.class);

    private static KafkaProducer<Object, String> producer;

    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

    private SimpleDateFormat sdf = new SimpleDateFormat();

    static {
        Properties props = new Properties();

        //xxx服务器ip
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.1.132.153:9092");
        //所有follower都响应了才认为消息提交成功，即"committed"
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        //retries = MAX 无限重试，直到你意识到出现了问题:)
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        //producer将试图批处理消息记录，以减少请求次数.默认的批量处理消息字节数
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        //producer可以用来缓存数据的内存大小。
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    private static void send() {
        long time1 = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {

                        int userType = randomUserType();
                        int userId = (userType == 1 ? 180326 : 90035) + finalI;

                        Map<String,Object> netStatus = new HashMap<>();

                        Date date = new Date();
                        netStatus.put("time", date);
                        netStatus.put("delay", randomDelay());
                        netStatus.put("lossrate", randomLossrate());
                        netStatus.put("shake", randomShake());
                        netStatus.put("roomId", finalI);
                        netStatus.put("userId", userId);
                        netStatus.put("userType", userType);

                        String data = JsonUtils.jsonEncode(netStatus);

//                            producer.send(new ProducerRecord<>("net-status", finalI % 10, date.getTime(), null, data), new Callback() {
                            producer.send(new ProducerRecord<>("net-status", data), new Callback() {
                            @Override
                            public void onCompletion(RecordMetadata metadata, Exception exception) {
                                logger.info("Push kafka message completed! offset: {}", metadata.offset());
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        long time2 = System.currentTimeMillis();
        System.out.println("Task time is " + (time2 - time1) + "ms");
    }

    private static int randomDelay() {
        //0-500ms
        Random random = new Random();
        return random.nextInt(500);
    }

    private static int randomLossrate() {
        //0-100%
        Random random = new Random();
        return random.nextInt(100);
    }

    private static int randomShake() {
        //0-2000
        Random random = new Random();
        return random.nextInt(2000);
    }

    private static int randomRoom() {
        //0-10
        Random random = new Random();
        return random.nextInt(10);
    }

    private static int randomUser() {
        //0-100
        Random random = new Random();
        return random.nextInt(100);
    }

    private static int randomUserType() {
        //0-1
        Random random = new Random();
        return random.nextInt(2);
    }

    public static void main(String[] args) throws InterruptedException {
        send();
        Thread.sleep(10000);
    }
}
