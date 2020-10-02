package com.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "customer_log")
public class CustomerLog {
    @TableId(type = IdType.AUTO)
    private Integer Id;
    private String CustomerId;
    private String CustomerName;
    private  String content;
    private  String AddTime;
}
