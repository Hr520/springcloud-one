package com.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role_menu")
public class RoleMenu {
    private Integer Id;
    private Integer  RoleId;// 角色名称
    private  Integer MenuId;
}
