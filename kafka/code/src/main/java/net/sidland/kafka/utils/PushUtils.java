/**
 * Project Name:kafkacode
 * File Name:SendUtils.java
 * Package Name:net.sidland.kafka.utils
 * Date:2015年9月10日上午11:13:05
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.kafka.utils;

import org.slf4j.Logger;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import net.sidland.kafka.init.InitKafka;
import utils.utils.DataTypeUtil;
import utils.utils.LogUtil;

/**
 * ClassName:SendUtils
 * Reason:	 发送消息工具. 
 * Date:     2015年9月9日 上午11:31:54 
 * @author   sid
 * @see 	 
 */
public class PushUtils {  
	private static Logger logger = LogUtil.getInstance().getLogger(PushUtils.class);

    private static ProducerConfig config = null;
    
    private static Producer<String, String> producer = null;
    
    private static ProducerConfig getConfig(){
    	if (DataTypeUtil.isNotEmpty(config)) {
			return config;
		}
    	config = new ProducerConfig(InitKafka.productor);
    	return config;
    }
    
    private static Producer<String, String> getProducer(){
    	if (DataTypeUtil.isNotEmpty(producer)) {
			return producer;
		}
    	producer = new Producer<String, String>(getConfig());
    	return producer;
    }
    
    /**
     * 
     * sendMsg:(发送消息到Kafka服务器). 
     *
     * @author sid
     * @param topic
     * @param key
     * @param msg
     */
    public static void sendMsg(String topic,String key,String msg){
    	logger.debug("发送消息到Kafka服务器："+topic+":"+key+"-"+msg);
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, key, msg);
        getProducer().send(data);
    }
}  