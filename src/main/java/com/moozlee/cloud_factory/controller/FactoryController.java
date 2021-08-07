package com.moozlee.cloud_factory.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.FactoryDto;
import com.moozlee.cloud_factory.dto.QueryDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.service.IFactoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@ApiRestController
public class FactoryController {
    ObjectMapper mapper;
    IFactoryService factoryService;

    public FactoryController(ObjectMapper mapper, IFactoryService factoryService) {
        this.mapper = mapper;
        this.factoryService = factoryService;
    }

    @GetMapping("/factories")
    public Result getFactoryList(QueryDto query) {
        FactoryDto factoryDto = new FactoryDto();

        try {
            factoryDto = mapper.readValue(query.getQuery(), FactoryDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        IPage<FactoryDto> page = factoryService.selectPage(new Page<>(query.getPageNumber(), query.getPageSize()), factoryDto);
        return new Result().code(200).message("获取成功").data(page);
    }

    @PutMapping("/factory/status/{id}")
    public Result updateStatus(@PathVariable("id") String id) {
        factoryService.updateStatusById(id);
        return new Result().code(200).message("修改成功");
    }
}
