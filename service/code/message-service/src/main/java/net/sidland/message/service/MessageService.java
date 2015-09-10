/**
 * Project Name:message-service
 * File Name:MessageService.java
 * Package Name:net.sidland.message.service
 * Date:2015年9月10日上午10:27:43
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sidland.message.dao.KafkaDao;

/**
 * 
 * ClassName: MessageService 
 * Reason: 消息逻辑的处理. 
 * date: 2015年9月10日 上午10:27:18 
 *
 * @author sid
 */
@Component
public class MessageService {
	@Autowired
	private KafkaDao msgDao;
	
	public void push(String msg){
		msgDao.push(msg);
	}
	
	public String pull(String msgId){
		return msgDao.pull(msgId);
	}
}
