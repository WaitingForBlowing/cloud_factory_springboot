package com.moozlee.cloud_factory.controller;

import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.MenuDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.service.IMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@ApiRestController
public class MenuController {

    private final IMenuService menuService;

    public MenuController(IMenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menus/{id}")
    public Result getMenus(@PathVariable("id") Integer id) {
        List<MenuDto> list = null;
        list = menuService.getMenus(id);
        return new Result().code(200).message("获取成功").data(list);
    }
}
