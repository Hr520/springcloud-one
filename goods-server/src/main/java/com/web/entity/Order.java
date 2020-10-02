package com.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "order")
public class Order {
    private Long uuid;
    private BigDecimal TotalSellingPrice;
    private BigDecimal TheAmountPaid;
    private  BigDecimal TotalPriceOfTheOffer;
    private  BigDecimal NetProfit;
    private Integer Status;
    private  String AddTime;
}
