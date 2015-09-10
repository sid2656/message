package net.sidland.message.utils;

import java.util.Properties;



public class InitMessage {
	
	/**
	 * 系统根目录
	 */
	public static final String ROOT_PATH = System.getProperty("user.dir");
	
	/**
	 * 配置文件地址
	 */
	private static final String charset="utf-8";
	public static final Properties application = PropertiesUtil.getProperties("application.properties", charset);
}
