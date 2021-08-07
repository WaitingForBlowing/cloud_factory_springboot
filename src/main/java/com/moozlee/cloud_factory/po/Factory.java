package com.moozlee.cloud_factory.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Factory {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField(value = "manager_id")
    private String manageId;

    @TableField(exist = false)
    private User manager;

    @TableField(value = "factory_name")
    private String factoryName;

    @TableField(value = "factory_info")
    private String factoryInfo;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "created_time")
    private Date createdTime;

    @TableField(value = "updated_time")
    private Date updatedTime;

    @TableField(value = "has_delete")
    private Integer hasDelete;
}
