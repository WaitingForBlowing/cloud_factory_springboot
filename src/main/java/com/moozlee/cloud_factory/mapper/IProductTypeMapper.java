package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.TypeDto;
import com.moozlee.cloud_factory.po.ProductType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductTypeMapper extends BaseMapper<ProductType> {

    IPage<TypeDto> findTypePageDetail(Page<TypeDto> page, @Param("type") TypeDto typeDto);

    List<TypeDto> findAll();
}
