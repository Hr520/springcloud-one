package com.web.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

/**
 *
 *
 * 商品表 含义参考明细
 *
 * 主表
 * 商品表 goods
 *     UUID  商品ID
 *     goods_name 商品名称
 *     goods_parent_id 分类ID
 *     status  状态
 *     pricing 定价
 *     price 原价
 *     cost  成本价
 *     add_time 入库时间
 *     add_date 入库日期
 *     valid_time 有效时间
 *     coding 商品编码
 *     expired_status 过期状态
 *     sort 排序
 *     update_time  更新时间
 * time  2020/08/30 w
 *
 */
@Data
@TableName("goods")
public class Goods {
    @TableId
    private Long uuid;
    private String GoodsName;
    private Integer GoodsParentId;
    private  Integer Status;
    private BigDecimal Pricing;
    private BigDecimal Price;
    private  BigDecimal Cost;
    private  String AddTime;
    private  String AddDate;
    private  String ValidTime;
    private  String Coding;
    private Integer ExpiredStatus;
    private Integer Sort;
    private  String UpdateTime;
}
