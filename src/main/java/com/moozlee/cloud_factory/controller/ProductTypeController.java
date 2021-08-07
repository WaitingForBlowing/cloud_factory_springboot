package com.moozlee.cloud_factory.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.QueryDto;
import com.moozlee.cloud_factory.dto.TypeDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.service.IProductTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiRestController
public class ProductTypeController {

    ObjectMapper mapper;
    IProductTypeService productTypeService;

    public ProductTypeController(ObjectMapper mapper, IProductTypeService productTypeService) {
        this.mapper = mapper;
        this.productTypeService = productTypeService;
    }

    @GetMapping("/product/types")
    public Result getProductTypeList(QueryDto query) {
        TypeDto type = new TypeDto();
        try {
            type = mapper.readValue(query.getQuery(), TypeDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        IPage<TypeDto> page = productTypeService.selectPage(new Page<>(query.getPageNumber(), query.getPageSize()), type);
        return new Result().code(200).message("获取成功").data(page);
    }

    @GetMapping("/product/types/all")
    public Result getAllProductTypeList() {
        List<TypeDto> all = productTypeService.getAll();
        return new Result().code(200).message("获取成功").data(all);
    }

    @PostMapping("/product/type")
    public Result addProductType(TypeDto dto) {
        productTypeService.insert(dto);
        return new Result().code(200).message("添加成功");
    }

    @GetMapping("/product/type/{id}")
    public Result selectTypeById(@PathVariable("id") String id) {
        TypeDto typeDto = productTypeService.selectById(id);
        return new Result().code(200).data(typeDto).message("获取成功");
    }

    @PutMapping("/product/type")
    public Result updateTypeById(TypeDto type) {
        productTypeService.updateById(type);
        return new Result().code(200).message("修改成功");
    }

    @DeleteMapping("/product/type/{id}")
    public Result removeTypeById(@PathVariable("id") String id) {
        productTypeService.removeById(id);
        return new Result().code(200).message("删除成功");
    }
}
