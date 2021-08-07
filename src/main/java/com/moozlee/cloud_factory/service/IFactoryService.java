package com.moozlee.cloud_factory.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.FactoryDto;

public interface IFactoryService {
    IPage<FactoryDto> selectPage(Page<FactoryDto> page, FactoryDto factoryDto);
    void updateStatusById(String id);
}
