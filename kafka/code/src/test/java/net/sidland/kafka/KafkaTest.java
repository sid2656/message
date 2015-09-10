/**
 * Project Name:kafkacode
 * File Name:KafkaTest.java
 * Package Name:net.sidland.kafka
 * Date:2015年9月10日上午11:23:17
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.kafka;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import junit.framework.TestCase;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.consumer.Whitelist;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.message.MessageAndMetadata;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import net.sidland.kafka.init.InitKafka;
import net.sidland.kafka.utils.PullUtils;
import net.sidland.kafka.utils.PushUtils;
import utils.utils.DataTypeUtil;
import utils.utils.LogUtil;

/**
 * ClassName:KafkaTest
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2015年9月10日 上午11:23:17 
 * @author   sid
 * @see 	 
 */
public class KafkaTest extends TestCase {

	public void sendMsgTest(){
		PushUtils.sendMsg("topic2", "test4", "msg4");
	}
	
	public void pullMsgTest(){
		PullUtils.pullMsg("groupid1", "topic2");
	}
	
	public void producerTest(){
		Logger logger = LogUtil.getInstance().getLogger(KafkaTest.class);
        long events = Long.parseLong("10");  
        Random rnd = new Random();  
        ProducerConfig config = new ProducerConfig(InitKafka.productor);  
   
        Producer<String, String> producer = new Producer<String, String>(config);  

        logger.info("productor start:");
        for (long nEvents = 0; nEvents < events; nEvents++) {
               long runtime = new Date().getTime();
               String ip = "192.168.2." + rnd.nextInt(255);
               String msg = runtime + ",www.example.com," + ip;
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("topic1", ip, msg);
               producer.send(data);
        }
        logger.info("productor end");
        producer.close();  
	}
	
	public void consumerTest(){
		try {
			Logger logger = LogUtil.getInstance().getLogger(KafkaTest.class);

			InitKafka.consumer.setProperty("group.id", "page_visits");
			ConsumerConfig consumerConfig = new ConsumerConfig(InitKafka.consumer);

			ConsumerConnector javaConsumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

			// topic的过滤器
			Whitelist whitelist = new Whitelist("topic1");
			List<KafkaStream<byte[], byte[]>> partitions = javaConsumerConnector.createMessageStreamsByFilter(whitelist);

			if (!DataTypeUtil.isNotEmpty(partitions)) {
				logger.info("partition为空！");
				TimeUnit.SECONDS.sleep(1);
			}

			// 消费消息
			for (KafkaStream<byte[], byte[]> partition : partitions) {
				ConsumerIterator<byte[], byte[]> iterator = partition.iterator();
				while (iterator.hasNext()) {
					MessageAndMetadata<byte[], byte[]> next = iterator.next();
					logger.info("partiton:" + next.partition());
					logger.info("offset:" + next.offset());
					logger.info("message:" + new String(next.message(), "utf-8"));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

