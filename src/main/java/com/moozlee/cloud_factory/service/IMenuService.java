package com.moozlee.cloud_factory.service;

import com.moozlee.cloud_factory.dto.MenuDto;

import java.util.List;

public interface IMenuService {

    List<MenuDto> getMenus(Integer id);
}
