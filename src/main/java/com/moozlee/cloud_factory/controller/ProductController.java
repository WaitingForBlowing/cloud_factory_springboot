package com.moozlee.cloud_factory.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.ProductDto;
import com.moozlee.cloud_factory.dto.QueryDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.service.IProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiRestController
public class ProductController {

    ObjectMapper mapper;
    IProductService productService;

    public ProductController(ObjectMapper mapper, IProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }

    @GetMapping("/product/infos")
    public Result getProductList(QueryDto query) {
        ProductDto product = new ProductDto();
        try {
            product = mapper.readValue(query.getQuery(), ProductDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        IPage<ProductDto> page = productService.selectPage(new Page<>(query.getPageNumber(), query.getPageSize()), product);
        return new Result().code(200).message("获取成功").data(page);
    }

    @PostMapping("/product/info")
    public Result addProduct(ProductDto productDto) {
        productService.insert(productDto);
        return new Result().code(200).message("添加成功");
    }

    @GetMapping("/product/info/{id}")
    public Result getProductById(@PathVariable("id") String id) {
        ProductDto productDto = productService.selectById(id);
        return new Result().code(200).message("获取成功").data(productDto);
    }

    @PutMapping("/product/info")
    public Result updateProductById(ProductDto productDto) {
        productService.updateById(productDto);
        return new Result().code(200).message("修改成功");
    }

    @DeleteMapping("/product/info/{id}")
    public Result removeProductById(@PathVariable("id") String id) {
        productService.removeById(id);
        return new Result().code(200).message("删除成功");
    }

    @GetMapping("/products/all")
    public Result getAllProduct() {
        List<ProductDto> list = productService.getAllProductList();
        return new Result().code(200).message("获取成功").data(list);
    }
}
