package com.moozlee.cloud_factory.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Equipment {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    @TableField("type_id")
    private String typeId;
    @TableField("name")
    private String name;
    @TableField("specification")
    private String specification;
    @TableField("description")
    private String description;
    @TableField("equipment_status")
    private Integer equipmentStatus;
    @TableField("rent_status")
    private Integer rentStatus;
    @TableField("factory_id")
    private String factoryId;
    @TableField("created_time")
    private String createdTime;
    @TableField("updated_time")
    private String updatedTime;
    @TableField("has_delete")
    private Integer hasDelete;
}
