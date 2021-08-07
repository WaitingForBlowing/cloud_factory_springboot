package com.moozlee.cloud_factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private String id;
    private String account;
    private String name;
    private String mail;
    private String tel;
    private String roleName;
}
