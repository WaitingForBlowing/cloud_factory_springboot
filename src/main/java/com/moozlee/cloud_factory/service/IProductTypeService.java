package com.moozlee.cloud_factory.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.TypeDto;

import java.util.List;

public interface IProductTypeService {
    IPage<TypeDto> selectPage(Page<TypeDto> page, TypeDto typeDto);
    List<TypeDto> getAll();
    void insert(TypeDto typeDto);
    TypeDto selectById(String id);
    void updateById(TypeDto typeDto);
    void removeById(String id);
}
