package com.moozlee.cloud_factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    private String id;
    private String typeName;
    private String name;
    private String specification;
    private String description;
}
