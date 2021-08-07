package com.moozlee.cloud_factory.controller.test;

import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.po.Result;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;

@ApiRestController
public class TestController {

    @PostMapping("/test/super")
    @RequiresRoles(logical = Logical.OR,value = {"super"})
    public Result user(){
        return new Result().code(200).message("成功访问super接口");
    };

    @PostMapping("/test/manager")
    @RequiresRoles(logical = Logical.OR,value = {"manager"})
    public Object manager() {
        return new Result().code(200).message("成功访问manager接口");
    };

    @PostMapping("/test/consignee")
    @RequiresRoles(logical = Logical.OR,value = {"consignee"})
    public Object consignee() {
        return new Result().code(200).message("成功访问consignee接口");
    };

}
