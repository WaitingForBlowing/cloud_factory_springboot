package com.moozlee.cloud_factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EquipmentDto {
    private String id;
    private String name;
    private String typeName;
    private String specification;
    private String desc;
    private Integer status;
    private Integer rentStatus;
    private String factoryName;
}
