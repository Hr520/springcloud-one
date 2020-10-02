package com.web.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共方法
 * 
 * @author admin
 *
 */
public class StringUtil {

	/**
	 * 获取32位唯一标示
	 * 
	 * @return
	 */
	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	/**
	 * 判断是否为空
	 * 
	 * @param b
	 * @return
	 */
	public static boolean isNull(Object b) {
		boolean result = false;
		if ("".equals(b) || b == null) {
			result = true;
		}
		return result;
	}



	/**
	 * 字符串转为数组(字符串以逗号隔开)
	 * 
	 * @param str
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",");
	}

	public static String[] str2StrArray(String str, String splitRegex) {
		if (isNull(str)) {
			return null;
		}
		return str.split(splitRegex);
	}


	/**
	 * 判断手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 2个时间直接的秒数
	 * 
	 * @param str1
	 *            开始时间
	 * @param str2
	 *            结束时间
	 * @return
	 */
	public static long getDistanceTimes(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long sec = 0;
		try {
			Date one = df.parse(str1);
			Date two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff = time2 - time1;
			sec = diff / 1000;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sec;
	}

	/**
	 * 日期格式时间转换为时间戳格式
	 * 
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public static String dateToStamp(String s) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		String res = String.valueOf(ts);
		return res;
	}

	/**
	 * 订单号
	 * 
	 * @return
	 */
	public static synchronized String getOrderNo() {
		String str = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		Random random = new Random();

		int x = random.nextInt(899999);

		return str + String.valueOf(x + 100000);
	}

	/**
	 * 判断对象为空
	 * 
	 * @param obj
	 *            对象名
	 * @return 是否为空
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if ((obj instanceof List)) {
			return ((List<?>) obj).size() == 0;
		}
		if ((obj instanceof String)) {
			return ((String) obj).trim().equals("");
		}
		return false;
	}

	/**
	 * 判断对象不为空
	 * 
	 * @param obj
	 *            对象名
	 * @return 是否不为空
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/*
	 * 为客户端生产签名
	 * 
	 */
	public static void main(String[] args) {
		// 生成签名
	}

	/**
	 * 获取一个唯一的帐号
	 * 
	 * @return
	 */
	public static synchronized String getStringCode() {
		// 生成一个唯一的帐号
		StringBuffer sb = new StringBuffer();
		String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int length = 15;
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		String UniqueAccount = "JJST" + sb.toString();
		return UniqueAccount;
	}

	/**
	 * 随机生成范围值的一个随机数
	 * 
	 * @param n
	 *            范围值
	 * @param m
	 *            生成数
	 * @return
	 */
	public static Integer getRandomArray(int n) {
		Random r = new Random();
		int i = r.nextInt(n);
		return i;
	}

}
