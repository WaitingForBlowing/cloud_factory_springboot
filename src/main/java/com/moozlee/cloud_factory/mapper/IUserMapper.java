package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.UserDto;
import com.moozlee.cloud_factory.po.Role;
import com.moozlee.cloud_factory.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserMapper extends BaseMapper<User> {

    Role findRoleByUsername(@Param("username") String username);
    IPage<UserDto> findUserPageDetail(Page<UserDto> page, @Param("user") UserDto userDto);
}
