package com.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "goods_type")
public class GoodsType {
    @TableId(type = IdType.AUTO)
    private Integer Id;
    private String ClassificationName;
    private  Integer Sort;
    private Integer Status;
    private  String AddTime;
}
