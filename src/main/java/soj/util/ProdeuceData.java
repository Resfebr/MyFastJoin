package soj.util;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class ProdeuceData {

    public static void produceData(String topic) throws Exception {

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "nimbus:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //定义Kafka Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        //用缓冲方式读取文本
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/root/1/" + topic));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, line);
            //发送数据
            kafkaProducer.send(producerRecord);
        }
        kafkaProducer.close();
    }
}
