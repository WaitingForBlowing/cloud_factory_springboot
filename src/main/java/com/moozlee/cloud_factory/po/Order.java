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
@TableName("`order`")
public class Order {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("product_id")
    private String productId;

    @TableField(exist = false)
    private Product product;

    @TableField("product_amount")
    private Integer productAmount;

    @TableField("accomplish_deadline")
    private Date accomplishDeadline;

    @TableField("tender_deadline")
    private Date tenderDeadline;

    @TableField("order_status")
    private Integer orderStatus;

    @TableField("consignee_id")
    private String consigneeId;

    @TableField(exist = false)
    private User consignee;

    @TableField("factory_id")
    private String factoryId;

    @TableField(exist = false)
    private Factory factory;

    @TableField("created_time")
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField("has_delete")
    private Integer hasDelete;
}
