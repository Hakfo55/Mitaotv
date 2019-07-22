package com.mountain.common.util;

import java.io.InputStream;

/**
 * 
* @Title: ResourceUtil.java 
* @Package com.slarts.common.util 
* @Description: TODO 读取resource里边的文件 
* @author porridge
* @date 2016年10月27日 下午10:12:33 
* @version V1.0
 */
public class ResourceUtil {
	private InputStream inStream;
	public ResourceUtil(String url){
			inStream = getClass().getClassLoader().getResourceAsStream(url);
	}
	public String getContent(){
		return FileUtil.readInputStream(inStream);
	}
	public static void main(String[] a){
//		String content = new ResourceUtil("images/lastPoster.jpg").getContent();
//		System.out.println(content.split("\n").length);
//		String posterPath =  this.getClass().getResource("/images/lastPoster.jpg").getPath();
	}
}
