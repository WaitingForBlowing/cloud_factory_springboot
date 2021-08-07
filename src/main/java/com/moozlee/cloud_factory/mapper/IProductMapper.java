package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.ProductDto;
import com.moozlee.cloud_factory.po.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductMapper extends BaseMapper<Product> {

    IPage<ProductDto> findProductPageDetail(Page<ProductDto> page, @Param("product") ProductDto productDto);

    List<ProductDto> findAll();
}
