package com.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role")
public class Role{

	/**
	 * 角色实体类
	 */
	private Integer Id;
	private String RoleName;// 角色名称
	private  String AddTime;
	private  String Remark;
	private  String ImgUrl;


}
