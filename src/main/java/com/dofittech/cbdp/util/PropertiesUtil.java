package com.dofittech.cbdp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties配置文件读取类
 * @author sh4n7ie  firheroicths@gmail.com
 * @date   2016年5月16日 下午1:55:51
 *
 */
public class PropertiesUtil {
	
	protected Properties properties;
	
	public PropertiesUtil(String resourceName){
		InputStream in = Object.class.getResourceAsStream(resourceName);
		 try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key){
		return properties.getProperty(key);
	}
	
	public int getInteger(String key){
		return Integer.valueOf(properties.getProperty(key));
	}
	
	public long getLong(String key){
		return Long.valueOf(properties.getProperty(key));
	}
	
	public boolean getBoolean(String key){
		return properties.get(key).equals("true") || properties.get(key).equals("1")?true:false;
	}
	
}
