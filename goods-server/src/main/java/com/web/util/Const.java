package com.web.util;

import org.springframework.context.ApplicationContext;

/**
 * 项目名称：
 * 
 * @author:fh
 * 
 */
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_menuList = "menuList"; // 当前菜单
	public static final String SESSION_ALLmenuList = "ALLmenuList"; // 全部菜单
	public static final String SESSION_USERNAME = "USERNAME"; // 用户名
	public static final String Role_id ="role_id";//用户角色
	public static final String Role_name="role_name";//角色名称
	public static final String Role_remark="Role_remark";//角色功能描述
	public static final String Role_img="Role_img";//角色头像
	public static final String Login_name="login_name";
	public static final String Msgcount="msgcount";//用户未读消息数量
	public static final String FILEPATHFILE = "upload/file/"; // 文件上传路径
	public static final String LOGIN = "/login"; // 登录地址
	public static final String WechatCode="openId";//微信用户的openid
    public static final String WechatUSERID="user_id";//微信用户的user_id
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(assets)|(bootstrap)|(build)|(dist)|(plugins)|(upload)|(logout)|(code)|(Jquery)|(api)|(main)|(interface)|(weixin)|(feign-consumer)).*"; // 不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; // 该值会在web容器启动时由WebAppContextListener初始化
	public  static  final  String White_IP=".*((localhost)|(127.0.0.1)|(172.16.3)|(210.42.109.72)|(te.m.hust.edu.cn)).*";

	public  static final String CACHE_SESSION_USER_MENU_="SESSION_USER_MENU_";//SESSION缓存用户菜单


	/**启用、有效**/
	public static final int STATUS_ENABLED = 1;
	/**禁用、无效**/
	public static final int STATUS_DISABLED = 2;
	

	/**角色：管理员 **/
	public static final String ROLE_ADMIN = "1";
	/**角色：教师 **/
	public static final String ROLE_TEACHER = "2";
	/**角色：学生 **/
	public static final String ROLE_STUDENT = "3";

	/**角色：课程负责人 、老师 **/
	public static final String ROLE_ADMIN_TEACHER = "4";

	/** REDIS 缓存key **/
	public  static final String CACHE_REDIS_USER_MENU_="USER_MENU_";//缓存用户菜单



}
