/**
 * Project Name:kafkacode
 * File Name:PullUtils.java
 * Package Name:net.sidland.kafka.utils
 * Date:2015年9月10日上午11:52:57
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.kafka.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;

import com.alibaba.fastjson.JSONArray;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import net.sidland.kafka.init.InitKafka;
import utils.utils.DataTypeUtil;
import utils.utils.LogUtil;

/**
 * ClassName:PullUtils
 * Reason:	 拉消息工具类 
 * Date:     2015年9月10日 上午11:52:57 
 * @author   sid
 * @see 	 
 */

public class PullUtils {
	
	private static Logger logger = LogUtil.getInstance().getLogger(PullUtils.class);
	
	public static String pullMsg(String groupid,String topic){
		logger.debug("PullUtils pullMsg star");
		ConsumerConnector connector = null;
		List<String> results = new ArrayList<String>();
    	try {
    		Properties consumer = (Properties) InitKafka.consumer.clone();
    		consumer.setProperty("group.id", groupid);
			ConsumerConfig consumerConfig = new ConsumerConfig(consumer);
			connector = Consumer.createJavaConsumerConnector(consumerConfig);
			// topic的过滤器
//			Whitelist whitelist = new Whitelist(topic);
//			List<KafkaStream<byte[], byte[]>> partitions = connector.createMessageStreamsByFilter(whitelist);

			// 消费消息
//			for (KafkaStream<byte[], byte[]> partition : partitions) {
//				ConsumerIterator<byte[], byte[]> iterator = partition.iterator();
//				while (DataTypeUtil.isNotEmpty(iterator)) {
//					MessageAndMetadata<byte[], byte[]> next = iterator.next();
//					logger.debug("partiton:" + next.partition());
//					logger.debug("offset:" + next.offset());
//					logger.debug("message:" + new String(next.message(), InitKafka.charset));
//					result.add(new String(next.message(), InitKafka.charset));
//				}
//			}
			
			
			

	        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
	        topicCountMap.put(topic, 1);  
	        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = connector.createMessageStreams(topicCountMap);  
	        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
	        for (KafkaStream<byte[], byte[]> kafkaStream : streams) {
	        	String result = new String(kafkaStream.iterator().next().message(), InitKafka.charset);
				logger.info("输出topic中是数据："+result);
				results.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (DataTypeUtil.isNotEmpty(connector)) {
				connector.shutdown();
			}
		}
		logger.debug("PullUtils pullMsg end");
		return JSONArray.toJSONString(results);
	}
}