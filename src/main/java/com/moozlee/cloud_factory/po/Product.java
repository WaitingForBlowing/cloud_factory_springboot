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
public class Product {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("type_id")
    private String typeId;

    @TableField(exist = false)
    private ProductType type;

    @TableField("name")
    private String name;

    @TableField("specification")
    private String specification;

    @TableField("description")
    private String description;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField("has_delete")
    private Integer hasDelete;
}
