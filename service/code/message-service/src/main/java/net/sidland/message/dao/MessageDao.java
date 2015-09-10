/**
 * Project Name:message-service
 * File Name:MessageDao.java
 * Package Name:net.sidland.message.dao
 * Date:2015年9月10日上午10:22:49
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/

package net.sidland.message.dao;
/**
 * ClassName:MessageDao
 * Reason:	 抽象接口用于消息的实际处理. 
 * Date:     2015年9月10日 上午10:22:49 
 * @author   sid
 * @see 	 
 */
public interface MessageDao {

	public void push(String msg);
	public String pull(String msgId);
}

