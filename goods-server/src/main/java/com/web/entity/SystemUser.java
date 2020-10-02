package com.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "system_user")
public class SystemUser {
    @TableId(type = IdType.AUTO)
    private Integer Id;
    private String SystemName;
    private String PasswordLogin;
    private  String Code;
    private Integer Status;
    private  String AddTime;
    private String Remark;
    private String NickName;
    private String ImagUrl;


}
