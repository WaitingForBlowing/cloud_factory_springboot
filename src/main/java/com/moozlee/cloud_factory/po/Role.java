package com.moozlee.cloud_factory.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(value = "identifier")
    private String identifier;

    @TableField(value = "name")
    private String name;

    @TableField(exist = false)
    private List<Permission> permissionList;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;

    @TableField(value = "has_delete")
    private Integer hasDelete;
}
