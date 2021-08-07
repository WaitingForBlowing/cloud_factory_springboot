package com.moozlee.cloud_factory.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.QueryDto;
import com.moozlee.cloud_factory.dto.RegisterDto;
import com.moozlee.cloud_factory.dto.UserDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.po.User;
import com.moozlee.cloud_factory.service.IUserService;
import com.moozlee.cloud_factory.utils.RedisUtil;
import com.moozlee.cloud_factory.utils.TokenUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

@ApiRestController
public class UserController {

    RedisUtil redisUtil;
    IUserService userService;
    ObjectMapper mapper;

    public UserController(RedisUtil redisUtil, IUserService service, ObjectMapper mapper) {
        this.redisUtil = redisUtil;
        this.userService = service;
        this.mapper = mapper;
    }

    @PostMapping("/login")
    public Result login(@NotNull String username, @NotNull String password, HttpServletResponse response) {
        System.out.println(username);
        System.out.println(password);
        User user = userService.login(username, password);
        System.out.println(user);
        if (user == null) {
            return new Result().code(400).message("用户名或密码有误");
        }
        Long currentTimeMillis = System.currentTimeMillis();
        String token = TokenUtil.sign(username, currentTimeMillis);
        RedisUtil.set(username, currentTimeMillis, TokenUtil.REFRESH_EXPIRE_TIME);
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        return new Result().code(200).message("登录成功").data(user);
    }

    @PostMapping(value = {"/register", "/user"})
    public Result register(RegisterDto registerDto) {
        userService.register(registerDto);
        return new Result().code(200).message("成功");
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        if (token != null && !token.equals("")) {
            String account = TokenUtil.getAccount(token);
            if (RedisUtil.hasKey(account)) {
                RedisUtil.del(account);
            }
        }
        return new Result().code(200).message("退出登录");
    }

    @GetMapping("/users")
    public Result getUserList(@NotNull QueryDto query) {
        UserDto userDto = new UserDto();
        try {
            userDto = mapper.readValue(query.getQuery(), UserDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        IPage<UserDto> page = userService.selectPage(new Page<>(query.getPageNumber(), query.getPageSize()), userDto);
        return new Result().code(200).message("查询成功").data(page);
    }

    @DeleteMapping("/user/{id}")
    public Result deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
        return new Result().code(200).message("删除成功");
    }

    @GetMapping("/user/{id}")
    public Result selectUserById(@PathVariable("id") String id) {
        UserDto userDto = userService.selectUserById(id);
        return new Result().code(200).message("获取成功").data(userDto);
    }

    @PutMapping("/user")
    public Result updateUser(UserDto user) {
        userService.updateUser(user);
        return new Result().code(200).message("修改成功");
    }

}
