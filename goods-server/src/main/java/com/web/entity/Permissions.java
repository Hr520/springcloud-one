package com.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Permissions")
public class Permissions {
    private String id;
    private String permissionsName;
}
