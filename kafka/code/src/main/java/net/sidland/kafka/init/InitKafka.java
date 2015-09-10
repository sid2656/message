package net.sidland.kafka.init;

import java.util.Properties;

import utils.utils.PropertiesUtil;



public class InitKafka {
	
	/**
	 * 系统根目录
	 */
	public static final String ROOT_PATH = System.getProperty("user.dir");
	
	/**
	 * 配置文件地址
	 */
	private static final String charset="utf-8";
	public static final Properties productor = PropertiesUtil.getProperties("dev/productor.properties", charset);
	public static final Properties consumer = PropertiesUtil.getProperties("dev/consumer.properties", charset);
}
