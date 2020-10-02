package com.web.service;

import com.web.entity.Menu;
import java.util.List;

public interface MenuService {
    List<Menu> getMenuInId(List<Integer> collect);

    Menu getMenuByUrl(String url);
}
