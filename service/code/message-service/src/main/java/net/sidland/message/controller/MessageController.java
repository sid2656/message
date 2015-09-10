/**
 * Project Name:mvc
 * File Name:UserController.java
 * Package Name:springTest.mvc.controller
 * Date:2015年8月25日下午12:06:55
 * Copyright (c) 2015, sid Jenkins All Rights Reserved.
 * 
 *
*/
package net.sidland.message.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sidland.message.service.MessageService;


/**
 * 
 * ClassName: MessageController 
 * Reason:	 增加message的controller. 
 * date: 2015年9月10日 上午10:12:26 
 *
 * @author sid
 */
@RestController
@RequestMapping(value="/message")
public class MessageController {
	private static Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Autowired
	private MessageService msgService;
	
	@RequestMapping(value="/push")
	public String push(HttpServletRequest request,HttpServletResponse response){
		logger.debug("controller push star");
		String id = ServletRequestUtils.getStringParameter(request, "id","哈哈");
		msgService.push(id);
		logger.debug("controller push end");
		return id;
	}
	
	@RequestMapping(value="/pull")
	public String pull(HttpServletRequest request,HttpServletResponse response){
		logger.debug("controller pull star");
		String id = ServletRequestUtils.getStringParameter(request, "id","哈哈");
		msgService.pull(id);
		logger.debug("controller pull end");
		return id;
	}
}

