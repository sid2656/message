/**
 * Project Name:kafkacode
 * File Name:s.java
 * Package Name:net.sidland.kafka
 * Date:2015年9月9日下午2:22:43
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.kafka;

import org.slf4j.Logger;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import utils.utils.LogUtil;

/**
 * ClassName:ConsumerTest
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2015年9月9日 下午2:22:43 
 * @author   sid
 * @see 	 
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class ConsumerTest implements Runnable {  
	Logger logger = LogUtil.getInstance().getLogger(ConsumerTest.class);
	private KafkaStream m_stream;  
    private int m_threadNumber;  
   
    public ConsumerTest(KafkaStream a_stream, int a_threadNumber) {  
        m_threadNumber = a_threadNumber;  
        m_stream = a_stream;  
    }  
   
	public void run() {  
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();  
        while (it.hasNext())  
        	logger.info("输出消息： " + m_threadNumber + ": " + new String(it.next().message()));  
        logger.info("关闭线程: " + m_threadNumber);  
    }  
}  