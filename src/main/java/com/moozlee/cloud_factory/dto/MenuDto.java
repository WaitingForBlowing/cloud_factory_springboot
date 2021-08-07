package com.moozlee.cloud_factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuDto {
    private Integer id;
    private String name;
    private String url;
    List<MenuDto> children = new ArrayList<>();
}
