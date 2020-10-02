package com.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "order_info")
public class OrderInfo {
    private Integer id;
    private String OrderId;
    private String GoodsName;
    private  String GoodsUuid;
    private  BigDecimal GoodsPricing;
    private BigDecimal GoodsPrice;
    private Integer GoodsNum;
    private Integer GoodsClassificationId;
    private Integer status;
    private  String Preferential;
    private String AddTime;
}
