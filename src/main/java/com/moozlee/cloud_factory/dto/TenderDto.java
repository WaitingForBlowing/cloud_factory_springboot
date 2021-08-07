package com.moozlee.cloud_factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TenderDto {
    private String id;
    private String managerName;
    private String factoryName;
    private String managerTel;
    private Double price;
}
