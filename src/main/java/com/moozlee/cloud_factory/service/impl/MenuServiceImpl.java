package com.moozlee.cloud_factory.service.impl;

import com.moozlee.cloud_factory.dto.MenuDto;
import com.moozlee.cloud_factory.mapper.IMenuMapper;
import com.moozlee.cloud_factory.po.Menu;
import com.moozlee.cloud_factory.service.IMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements IMenuService {

    private final IMenuMapper mapper;

    public MenuServiceImpl(IMenuMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<MenuDto> getMenus(Integer roleId) {
        List<MenuDto> menus = new ArrayList<>();
        List<Menu> menuList = mapper.findByRoleId(roleId);
        System.out.println(menuList);
        return produceTypeTree(menuList);
    }

    public List<MenuDto> produceTypeTree(List<Menu> list) {
        ArrayList<MenuDto> menus = new ArrayList<>();
        Set<MenuDto> set = new HashSet<>();
        for (Menu m : list) {
            if (m.getParentId().equals(m.getId())) {
                MenuDto dto = new MenuDto();
                dto.setId(m.getId());
                dto.setName(m.getName());
                dto.setUrl(m.getUrl());
                dto.setChildren(new ArrayList<>());
                menus.add(dto);
                set.add(dto);
            }
        }

        for (Menu m : list) {
            Integer parentId = m.getParentId();
            for (MenuDto dto : set) {
                if (parentId.equals(dto.getId()) && !m.getId().equals(parentId)) {
                    MenuDto menuDto = new MenuDto();
                    menuDto.setId(m.getId());
                    menuDto.setName(m.getName());
                    menuDto.setUrl(m.getUrl());
                    menuDto.setChildren(new ArrayList<>());
                    dto.getChildren().add(menuDto);
                    set.add(menuDto);
                    break;
                }
            }
        }
        return menus;
    }
}
