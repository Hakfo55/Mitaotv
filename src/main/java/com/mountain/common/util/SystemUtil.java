package com.mountain.common.util;

import com.mountain.common.exception.common.CommonException;
import com.mountain.common.util.response.GlobalStatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 系统工具类
* @Title: SystemUtil.java 
* @Package xbed.com.cn.util 
* @Description: TODO
* @author porridge  
* @date 2015年11月27日 上午10:25:44 
* @version V1.0
 */
@SuppressWarnings("all")
public class SystemUtil {
	static Logger logger = LoggerFactory.getLogger(SystemUtil.class.getClass());
	/**
	 * 获得请求地址ip
	 * @param httpservletrequest
	 * @return
	 */
	public static String getClientIP(HttpServletRequest httpservletrequest) {
	    if (httpservletrequest == null)
	        return null;
	    String s = httpservletrequest.getHeader("X-Forwarded-For");
	    if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
	        s = httpservletrequest.getHeader("Proxy-Client-IP");
	    if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
	        s = httpservletrequest.getHeader("WL-Proxy-Client-IP");
	    if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
	        s = httpservletrequest.getHeader("HTTP_CLIENT_IP");
	    if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
	        s = httpservletrequest.getHeader("HTTP_X_FORWARDED_FOR");
	    if (s == null || s.length() == 0 || "unknown".equalsIgnoreCase(s))
	        s = httpservletrequest.getRemoteAddr();
	    return s;
	}
	/**
	 * 合并二个对象,将 obj2的值合并到 obj1里边去
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public static void mergeBean(Object obj1, Object obj2) throws CommonException {
		if(obj1.getClass() != obj2.getClass()){
            throw new CommonException(GlobalStatusCode.CODE_400018.code(), GlobalStatusCode.CODE_400018.value());
		}
		try {
			Field[] f1 = obj1.getClass().getDeclaredFields();
			Field[] f2 = obj2.getClass().getDeclaredFields();
			for(int i=0;i<f2.length;i++){
				f2[i].setAccessible(true);
				f1[i].setAccessible(true);
				if(f2[i].get(obj2) != null && f1[i].get(obj1) == null){
					f1[i].set(obj1, f2[i].get(obj2));
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 复制bean 允许不同对象复制,　只要他们之间有相同类型的属性名即可
	 * @param obj1 待复制的对象
	 * @param cls  需要复制的对象 class
	 */
	@SuppressWarnings("unchecked")
	public static <T> T copyBean(Object obj1, Class<?> cls){
		try {
			Object obj2 = cls.newInstance();
			Field[] f1s = obj1.getClass().getDeclaredFields();
			Field[] f2s = obj2.getClass().getDeclaredFields();
			for(int i=0;i<f1s.length;i++){
				Field f1 = f1s[i];
				for(int j=0;j<f2s.length;j++){
					Field f2 = f2s[j];
					if(f1.getName().equals(f2.getName()) && f1.getType() == f2.getType()){
						f1.setAccessible(true);
						f2.setAccessible(true);
						f2.set(obj2, f1.get(obj1));
					}
				}
			}
			return (T)obj2;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("finally")
	public static String MD5(String password){
		MessageDigest md5;
		StringBuffer result = new StringBuffer();
		try {
			md5 = MessageDigest.getInstance("MD5");
	        byte[] bytes = md5.digest(password.getBytes());
	        for(byte b : bytes)
	        {
	            String temp = Integer.toHexString(b & 0xff);
	            if(temp.length() == 1){
	                result.append("0" + temp);
	            }else{
	            	result.append(temp);
	            }
	        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}finally{
			return result.toString();
		}
	}
	/**
	 * 获得用户体验更好的时间返回方式
	 * 2分钟以内        显示 刚刚
	 * 一个钟以内      显示 x分钟前
	 * 一天以内          显示  xx:xx
	 * 一天以上          显示 xxxx-xx-xx 
	 * @param createdAt
	 * @return
	 */
	public static String getDateInfo(Date createdAt){
		if(createdAt == null) {
            return "";
        }
		long minute = (new Date().getTime() - createdAt.getTime()) / (60 * 1000);//分钟数
		if(minute <= 2){
			return "刚刚";
		}else if(minute < 60){
			return minute + "分钟前";
		}else if(minute < 1440){
			return new SimpleDateFormat("HH : mm").format(createdAt);
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(createdAt);
	}
	/**
	 * 获得随机数
	 * @param digi
	 * @return
	 */
	public static String getRandomStr(int digi) {
		StringBuffer buf = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i < digi; i++) {
			buf.append(r.nextInt(10));
		}
		return buf.toString();
	}
	
	public static String getBodyString(BufferedReader br) {
		 String inputLine;
	     String str = "";
	     try {
	       while ((inputLine = br.readLine()) != null) {
	        str += inputLine;
	       }
	       br.close();
	     } catch (IOException e) {
	    	 e.printStackTrace();
	    	 logger.error("e", e);
	     }
	     return str;
	 }

	public static String getUUID() {
		return UUID.randomUUID ().toString ().replace ("-", "");
	 }

	 public static String getOrderNo() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		return sdf.format(new Date()) + getRandomStr(5);
	}

}
