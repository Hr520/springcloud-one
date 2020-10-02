package com.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "customer")
public class Customer {
    private String uuid;
    private String CustomerName;
    private String birthday;
    private  Integer phone;
    private BigDecimal TotalSalesPoints;
    private  BigDecimal TotalAmount;
    private Integer Level;
}
