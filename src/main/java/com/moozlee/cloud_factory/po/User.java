package com.moozlee.cloud_factory.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("`user`")
public class User {
    @TableId(type = IdType.ASSIGN_UUID, value = "id")
    private String id;

    @TableField(value = "account")
    private String account;

    @TableField(value = "password")
    private String password;

    @TableField(value = "name")
    private String name;

    @TableField(value = "mail")
    private String mail;

    @TableField(value = "tel")
    private String tel;

    @TableField(value = "address")
    private String address;

    @TableField(value = "role")
    private Integer roleId;

    @TableField(exist = false)
    private Role role;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;

    @TableField(value = "has_delete")
    private Integer hasDelete = 0;

    public User(String id, String account, String password, String name, String mail, String tel, Date createdTime, Date updatedTime, Integer hasDelete) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.mail = mail;
        this.tel = tel;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.hasDelete = hasDelete;
    }
}
