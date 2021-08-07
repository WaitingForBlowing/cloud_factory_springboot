package com.moozlee.cloud_factory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegisterDto {
    private String account;
    private String password;
    private String name;
    private String tel;
    private String mail;
    private Integer type;
    private String address;
    private String factoryName;
    private String factoryDesc;
}
