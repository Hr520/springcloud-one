package com.web.controller.api;

import com.web.base.BaseController;
import com.web.service.MenuService;
import com.web.util.General;
import com.web.util.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.MenuVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menuApi")
public class MenuApi extends BaseController {
    @Resource
    private MenuService menuService;

    @PostMapping("/MenuList")
    public ResponseEntity<?> MenuList() {
        List<MenuVo> list = new ArrayList<>();
        return new ResponseEntity<>().setState(General.SUCCESS).setData(list).setMsg("数据获取成功");
    }
}
