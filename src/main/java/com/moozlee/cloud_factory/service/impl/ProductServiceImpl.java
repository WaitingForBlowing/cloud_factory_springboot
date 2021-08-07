package com.moozlee.cloud_factory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.ProductDto;
import com.moozlee.cloud_factory.mapper.IProductMapper;
import com.moozlee.cloud_factory.mapper.IProductTypeMapper;
import com.moozlee.cloud_factory.po.Product;
import com.moozlee.cloud_factory.po.ProductType;
import com.moozlee.cloud_factory.service.IProductService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class ProductServiceImpl implements IProductService {

    private final IProductMapper productMapper;
    private final IProductTypeMapper productTypeMapper;

    public ProductServiceImpl(IProductMapper productMapper, IProductTypeMapper productTypeMapper) {
        this.productMapper = productMapper;
        this.productTypeMapper = productTypeMapper;
    }

    @Override
    public IPage<ProductDto> selectPage(Page<ProductDto> page, ProductDto productDto) {
        return productMapper.findProductPageDetail(page, productDto);
    }

    @Override
    public void insert(ProductDto productDto) {
        Product product = new Product(UUID.randomUUID().toString(), productDto.getTypeName(), null, productDto.getName(), productDto.getSpecification(), productDto.getDescription(), new Date(), new Date(), 0);
        productMapper.insert(product);
    }

    @Override
    public ProductDto selectById(String id) {
        Product product = productMapper.selectById(id);
        ProductDto productDto = new ProductDto(product.getId(), "", product.getName(), product.getSpecification(), product.getDescription());
        ProductType productType = productTypeMapper.selectById(product.getTypeId());
        productDto.setTypeName(productType.getName());
        return productDto;
    }

    @Override
    public void updateById(ProductDto productDto) {
        Product product = productMapper.selectById(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setTypeId(productDto.getTypeName());
        product.setSpecification(productDto.getSpecification());
        productMapper.updateById(product);
    }

    @Override
    public void removeById(String id) {
        Product product = productMapper.selectById(id);
        product.setHasDelete(1);
        productMapper.updateById(product);
    }

    @Override
    public List<ProductDto> getAllProductList() {
        return productMapper.findAll();
    }
}
