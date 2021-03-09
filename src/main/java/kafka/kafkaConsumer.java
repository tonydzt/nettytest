package kafka;

import org.apache.kafka.clients.consumer.*;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author douzhitong
 * @date 2018/11/27
 */
public class kafkaConsumer {

    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(20);
    private static AtomicInteger num = new AtomicInteger(10);
    private static long time1;

    public static void main(String[] args) {
        for (int i = 0; i < num.get(); i++) {
            threadPoolExecutor.submit(new ClassOverTask(i));
            System.out.println(String.format("consumer%s created", i));
        }

        while (num.get() > 0) {
        }
        long time2 = System.currentTimeMillis();
        System.out.println("Task time is " + (time2 - time1) + "ms");
    }

    private static class ClassOverTask implements Runnable {

        private int index;

        private ClassOverTask(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            // 可消费多个topic,组成一个list
            Consumer<String, String> consumer = getConsumer(index);
            consumer.subscribe(Collections.singletonList("learning-class-over"));

//            consumer.seekToBeginning(consumer.assignment());

            while (consumer.assignment().size() == 0) {
                //poll才能触发assignment，不然不会分配partion
                ConsumerRecords<String, String> records = consumer.poll(1000);
            }

            System.out.println(String.format("thread %s assignment is %s!", Thread.currentThread().getName(), consumer.assignment().toString()));
            time1 = System.currentTimeMillis();

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                if (records.count() == 0) {
                    num.getAndDecrement();
                    System.out.println(String.format("thread %s is done!", Thread.currentThread().getName()));
                    break;
                }
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format("thread: %s, offset: %s, key: %s, value: %s, partition: %s", Thread.currentThread().getName(), record.offset(), record.key(), record.value(), record.partition()));
                }
            }
        }
    }

    private static Consumer<String, String> getConsumer(int i) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-classroom-api-classover");
        //最多一次获取5条信息，以5条为单位进行commitSync
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, String.valueOf(i));

        return new KafkaConsumer<>(properties);
    }
}
