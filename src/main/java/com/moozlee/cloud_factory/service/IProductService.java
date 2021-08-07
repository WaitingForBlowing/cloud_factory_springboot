package com.moozlee.cloud_factory.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.ProductDto;

import java.util.List;

public interface IProductService {
    IPage<ProductDto> selectPage(Page<ProductDto> page, ProductDto productDto);
    void insert(ProductDto productDto);
    ProductDto selectById(String id);
    void updateById(ProductDto productDto);
    void removeById(String id);
    List<ProductDto> getAllProductList();
}
