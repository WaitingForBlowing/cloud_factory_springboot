package com.moozlee.cloud_factory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.TypeDto;
import com.moozlee.cloud_factory.mapper.IProductTypeMapper;
import com.moozlee.cloud_factory.po.ProductType;
import com.moozlee.cloud_factory.service.IProductTypeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductTypeServiceImpl implements IProductTypeService {

    IProductTypeMapper typeMapper;

    public ProductTypeServiceImpl(IProductTypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public IPage<TypeDto> selectPage(Page<TypeDto> page, TypeDto typeDto) {
        return typeMapper.findTypePageDetail(page, typeDto);
    }

    @Override
    public List<TypeDto> getAll() {
        return typeMapper.findAll();
    }

    @Override
    public void insert(TypeDto typeDto) {
        String id = UUID.randomUUID().toString();
        ProductType type = new ProductType(id, typeDto.getName(), id, null, new Date(), new Date(), 0);
        type.setParent(type);
        typeMapper.insert(type);
    }

    @Override
    public TypeDto selectById(String id) {
        ProductType productType = typeMapper.selectById(id);
        return new TypeDto(productType.getId(), productType.getName());
    }

    @Override
    public void updateById(TypeDto typeDto) {
        ProductType productType = typeMapper.selectById(typeDto.getId());
        productType.setName(typeDto.getName());
        typeMapper.updateById(productType);
    }

    @Override
    public void removeById(String id) {
        ProductType type = typeMapper.selectById(id);
        type.setHasDelete(1);
        typeMapper.updateById(type);
    }
}
