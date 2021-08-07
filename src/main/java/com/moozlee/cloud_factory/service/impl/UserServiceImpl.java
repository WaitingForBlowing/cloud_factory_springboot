package com.moozlee.cloud_factory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.RegisterDto;
import com.moozlee.cloud_factory.dto.UserDto;
import com.moozlee.cloud_factory.mapper.IFactoryMapper;
import com.moozlee.cloud_factory.mapper.IRoleMapper;
import com.moozlee.cloud_factory.mapper.IUserMapper;
import com.moozlee.cloud_factory.po.Factory;
import com.moozlee.cloud_factory.po.Role;
import com.moozlee.cloud_factory.po.User;
import com.moozlee.cloud_factory.service.IUserService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserMapper userMapper;
    private final IRoleMapper roleMapper;
    private final IFactoryMapper factoryMapper;

    public UserServiceImpl(IUserMapper userMapper, IRoleMapper roleMapper, IFactoryMapper factoryMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.factoryMapper =factoryMapper;
    }

    @Override
    public User login(@NotNull String username, @NotNull String password) {
        Map<String, Object> selectMap = new HashMap<>();
        selectMap.put("account", username);
        selectMap.put("password", password);
        selectMap.put("has_delete", 0);
        QueryWrapper<User> query = new QueryWrapper<>();
        query.allEq(selectMap);
        return userMapper.selectOne(query);
    }

    @Override
    public void register(RegisterDto dto) {
        User user = new User(UUID.randomUUID().toString(), dto.getAccount(), dto.getPassword(), dto.getName(), dto.getMail(), dto.getTel(), new Date(), new Date(), 0);
        if (dto.getType() == 0) { // 云工厂管理员
            //查询角色
            QueryWrapper<Role> query = new QueryWrapper<>();
            query.eq("name", "云工厂管理员");// todo 耦合过大，需要改动
            Role role = roleMapper.selectOne(query);
            user.setRole(role);
            user.setRoleId(role.getId());

            //新增用户
             userMapper.insert(user);

             //新增工厂
            Factory factory = new Factory(UUID.randomUUID().toString(), user.getId(), user, dto.getFactoryName(), dto.getFactoryDesc(), 1, new Date(), new Date(), 0);
            factoryMapper.insert(factory);
        } else if (dto.getType() == 1) { // 经销商
            //查询角色
            QueryWrapper<Role> query = new QueryWrapper<>();
            query.eq("name", "经销商");// todo 耦合过大，需要改动
            Role role = roleMapper.selectOne(query);
            user.setRole(role);
            user.setRoleId(role.getId());

            //新增用户
            userMapper.insert(user);
        }
    }

    @Override
    public void deleteUser(String id) {
        User user = userMapper.selectById(id);
        user.setHasDelete(1);
        userMapper.updateById(user);
    }


    @Override
    public IPage<UserDto> selectPage(Page<UserDto> page, UserDto userDto) {
        return userMapper.findUserPageDetail(page, userDto);
    }

    @Override
    public UserDto selectUserById(String id) {
        User user = userMapper.selectById(id);
        Role role = roleMapper.selectById(user.getRoleId());
        return new UserDto(user.getId(), user.getAccount(), user.getName(), user.getMail(), user.getTel(), role.getName());
    }

    @Override
    public void updateUser(UserDto user) {
        User oldUser = userMapper.selectById(user.getId());
        oldUser.setAccount(user.getAccount());
        oldUser.setName(user.getName());
        oldUser.setMail(user.getMail());
        oldUser.setTel(user.getTel());
        userMapper.updateById(oldUser);
    }
}
