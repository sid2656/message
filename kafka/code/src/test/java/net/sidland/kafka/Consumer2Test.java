/**
 * Project Name:kafkacode
 * File Name:t.java
 * Package Name:net.sidland.kafka
 * Date:2015年9月9日下午3:09:54
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.kafka;

/**
 * ClassName:t
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2015年9月9日 下午3:09:54 
 * @author   sid
 * @see 	 
 */
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.consumer.Whitelist;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import net.sidland.kafka.init.InitKafka;
import utils.utils.DataTypeUtil;
import utils.utils.LogUtil;

public class Consumer2Test {

	public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
		Logger logger = LogUtil.getInstance().getLogger(Consumer2Test.class);

    	InitKafka.consumer.setProperty("group.id", "page_visits");
		ConsumerConfig consumerConfig = new ConsumerConfig(InitKafka.consumer);

		ConsumerConnector javaConsumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

		// topic的过滤器
		Whitelist whitelist = new Whitelist("page_visits");
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
	}
}