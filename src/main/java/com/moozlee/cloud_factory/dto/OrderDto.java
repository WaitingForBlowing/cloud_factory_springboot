package com.moozlee.cloud_factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private String id;
    private String productName;
    private Integer productAmount;
    private String accomplishDeadline;
    private String tenderDeadline;
    private Integer orderStatus;
    private String consigneeName;
    private String consigneeTel;
    private String consigneeAddress;
    private String factoryName = "-";
}
