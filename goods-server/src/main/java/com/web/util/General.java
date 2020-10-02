package com.web.util;

public class General {

	/**
	 * 请求成功
	 */
	public static final String SUCCESS = "0000";

	/**
	 * 修改成功
	 */
	public static final String SUCCESS_RE = "0001";

	/**
	 * 请求参数错误
	 */
	public static final String ERROR_1001 = "1001";

	/**
	 * 用户不存在
	 */
	public static final String ERROR_1002 = "1002";

	/**
	 * 暂无数据
	 */
	public static final String ERROR_0000 = "1111";

	/**
	 * 目标数据已存在
	 */
	public static final String Already = "2222";

	/**
	 * 系统错误
	 */
	public static final String ERROR_2001 = "2001";

	/**
	 * 定义web端16字节 私钥
	 * 
	 */
	public static String privatekeys = "JJST_WL_YUNSMART";

	// sign
	public static String SIGN = "JJST_WL_SIGNOUTH";

	public  static final Integer STATUS_EFFECTIVE=1;//状态有效
	public  static final Integer STATUS_INVALID=0;//状态无效


	/**
	 * 
	 * 百度语音配置
	 * 
	 */
	
	
	   //  填写网页上申请的appkey 如 $apiKey="g8eBUMSokVB1BHGmgxxxxxx"
	public static final String appKey = "9T7rHGc5I7CQ6Cmszu2cPixj";

 // 填写网页上申请的APP SECRET 如 $secretKey="94dc99566550d87f8fa8ece112xxxxx"
	public static final String secretKey = "8d3c042cf25294bbe6b2ff9e0a4cfac6";


 // text 的内容为"欢迎使用百度语音合成"的urlencode,utf-8 编码
 // 可以百度搜索"urlencode"
	public static String text = "欢迎使用百度语音";

 // 发音人选择, 0为普通女声，1为普通男生，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女声
	public static final int per = 0;
 // 语速，取值0-9，默认为5中语速
	public static final int spd = 5;
 // 音调，取值0-9，默认为5中语调
	public static final int pit = 5;
 // 音量，取值0-9，默认为5中音量
	public static final int vol = 5;

	public static final String url = "http://tsn.baidu.com/text2audio"; // 可以使用https

	public static String cuid = "1234567JAVA";
	

}
