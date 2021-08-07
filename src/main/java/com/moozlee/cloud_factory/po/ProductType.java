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
public class ProductType {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "parent_id")
    private String parentId;
    @TableField(exist = false)
    private ProductType parent;
    @TableField(value = "created_time")
    private Date createdTime;
    @TableField(value = "updated_time")
    private Date updatedTime;
    @TableField(value = "has_delete")
    private Integer hasDelete;
}
