package com.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "customer_integral_log")
public class CustomerIntegralLog {
    @TableId(type = IdType.AUTO)
    private Integer Id;
    private String CustomerId;
    private String CustomerName;
    private BigDecimal Integral;
    private String AddTime;
    private  String remark;
}
