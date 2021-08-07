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
public class Tender {
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("order_id")
    private String orderId;

    @TableField(exist = false)
    private Order order;

    @TableField("manager_id")
    private String managerId;

    @TableField(exist = false)
    private User manager;

    @TableField("price")
    private Double price;

    @TableField("status")
    private Integer status;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField("has_delete")
    private Integer hasDelete;
}
