/**
 * Project Name:kafkacode
 * File Name:s.java
 * Package Name:net.sidland.kafka
 * Date:2015年9月9日下午2:25:09
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import net.sidland.kafka.init.InitKafka;
import utils.utils.LogUtil;

/**
 * ClassName:s
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2015年9月9日 下午2:25:09 
 * @author   sid
 * @see 	 
 */
@SuppressWarnings("rawtypes")
public class ConsumerGroupTest {  
	Logger logger = LogUtil.getInstance().getLogger(ConsumerGroupTest.class);
    private final ConsumerConnector consumer;  
    private final String topic;  
    private  ExecutorService executor;  
   
    public ConsumerGroupTest(String groupId, String topic) {  
        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig(groupId));  
        this.topic = topic;  
    }  
   
    public void shutdown() {  
        if (consumer != null) consumer.shutdown();  
        if (executor != null) executor.shutdown();  
    }  
   
	public void run(int numThreads) {  
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
        topicCountMap.put(topic, new Integer(numThreads));  
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);  
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
        for (KafkaStream<byte[], byte[]> kafkaStream : streams) {
			logger.info("输出topic中是数据："+kafkaStream);
		}
   
        // 启动所有线程  
        executor = Executors.newFixedThreadPool(numThreads);  
   
        // 开始消费消息  
        int threadNumber = 0;  
        for (final KafkaStream stream : streams) {  
        	logger.info("开始消费："+stream.toString());
            executor.submit(new ConsumerTest(stream, threadNumber));  
            threadNumber++;  
        }  
    }  
   
    private static ConsumerConfig createConsumerConfig(String groupId) {
    	InitKafka.consumer.setProperty("group.id", groupId);
        return new ConsumerConfig(InitKafka.consumer);  
    }  
   
    public static void main(String[] args) {
    	ConsumerGroupTest example = new ConsumerGroupTest("test6", "topic1");  
        example.run(5);  
        try {  
            Thread.sleep(10000);  
        } catch (InterruptedException ie) {  
   
        }  
//        example.shutdown();  
    }  
}  