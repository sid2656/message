/**
 * Project Name:kafkacode
 * File Name:s.java
 * Package Name:net.sidland.kafka
 * Date:2015年9月9日上午11:31:54
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.kafka;

import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import net.sidland.kafka.init.InitKafka;
import utils.utils.LogUtil;

/**
 * ClassName:s
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2015年9月9日 上午11:31:54 
 * @author   sid
 * @see 	 
 */
public class ProducerTest {  
	public static void main(String[] args) {  
		Logger logger = LogUtil.getInstance().getLogger(ProducerTest.class);
        long events = Long.parseLong("10");  
        Random rnd = new Random();  
        ProducerConfig config = new ProducerConfig(InitKafka.productor);  
   
        Producer<String, String> producer = new Producer<String, String>(config);  

        logger.info("productor start:");
        for (long nEvents = 0; nEvents < events; nEvents++) {
               long runtime = new Date().getTime();
               String ip = "192.168.2." + rnd.nextInt(255);
               String msg = runtime + ",www.example.com," + ip;
               KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
               producer.send(data);
        }
        logger.info("productor end");
        producer.close();  
    }  
	
}  