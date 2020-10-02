package com.web.service;

import com.web.entity.GoodsType;

import java.util.List;

public interface GoodsTypeService {
    List<GoodsType> GoodsTypeList(Integer status);
}
