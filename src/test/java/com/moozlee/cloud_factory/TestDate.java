package com.moozlee.cloud_factory;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.MenuDto;
import com.moozlee.cloud_factory.dto.UserDto;
import com.moozlee.cloud_factory.mapper.IMenuMapper;
import com.moozlee.cloud_factory.mapper.IRoleMapper;
import com.moozlee.cloud_factory.mapper.IUserMapper;
import com.moozlee.cloud_factory.po.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class TestDate {

    @Autowired
    IMenuMapper mapper;

    @Autowired
    IRoleMapper roleMapper;

    @Autowired
    IUserMapper userMapper;

    @Test
    public void testMenu() {
        List<Menu> byRoleId = mapper.findByRoleId(1);
        List<MenuDto> dtos = produceTypeTree(byRoleId);
        System.out.println(dtos);
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

    @Test
    public void test2() {
        IPage<UserDto> userPageDetail = userMapper.findUserPageDetail(new Page<>(0, 2), new UserDto());
        System.out.println(userPageDetail.getRecords());
    }
}
