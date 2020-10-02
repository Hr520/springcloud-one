package com.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.TypeAlias;

@Data
@TableName("sys_menu")
public class Menu{

	/**
	 * 菜单实体类
	 */

	private Integer Id;
	private String MenuName;
	private String MenuUrl;
	@TableField("p_id")
	private Integer Pid;
	private Integer sort;
	private String MenuPic;

}
