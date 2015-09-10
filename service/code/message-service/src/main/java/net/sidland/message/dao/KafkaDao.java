/**
 * Project Name:message-service
 * File Name:Kafka.java
 * Package Name:net.sidland.message.dao
 * Date:2015年9月10日上午10:25:30
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.message.dao;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.sidland.kafka.utils.PullUtils;
import net.sidland.kafka.utils.PushUtils;

/**
 * ClassName:Kafka
 * Reason:	 kafka消息处理类. 
 * Date:     2015年9月10日 上午10:25:30 
 * @author   sid
 * @see 	 
 */
@Component
public class KafkaDao implements MessageDao {
	
	protected static Logger logger = LoggerFactory.getLogger(KafkaDao.class);

	@Override
	public void push(String msgId,String msg) {
		logger.debug("Kafka push start");

		PushUtils.sendMsg(msgId, UUID.randomUUID().toString(), msg);

		logger.debug("Kafka push end");
	}

	@Override
	public String pull(String groupid,String msgId) {
		return PullUtils.pullMsg(groupid, msgId);
	}

}

