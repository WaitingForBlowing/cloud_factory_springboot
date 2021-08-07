package com.moozlee.cloud_factory.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.RegisterDto;
import com.moozlee.cloud_factory.dto.UserDto;
import com.moozlee.cloud_factory.po.User;

public interface IUserService {
    User login(String username, String password);
    void register(RegisterDto dto);
    void deleteUser(String id);
    IPage<UserDto> selectPage(Page<UserDto> page, UserDto userDto);
    UserDto selectUserById(String id);
    void updateUser(UserDto user);
}
