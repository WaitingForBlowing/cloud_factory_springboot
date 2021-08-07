package com.moozlee.cloud_factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FactoryDto {
    private String id;
    private String name;
    private String desc;
    private String managerName;
    private String managerTel;
    private String managerAccount;
    private Integer status;
}
