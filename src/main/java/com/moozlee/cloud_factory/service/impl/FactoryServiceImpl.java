package com.moozlee.cloud_factory.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.FactoryDto;
import com.moozlee.cloud_factory.mapper.IFactoryMapper;
import com.moozlee.cloud_factory.po.Factory;
import com.moozlee.cloud_factory.service.IFactoryService;
import org.springframework.stereotype.Service;

@Service
public class FactoryServiceImpl implements IFactoryService {

    IFactoryMapper factoryMapper;

    public FactoryServiceImpl(IFactoryMapper factoryMapper) {
        this.factoryMapper = factoryMapper;
    }

    @Override
    public IPage<FactoryDto> selectPage(Page<FactoryDto> page, FactoryDto factoryDto) {
        return factoryMapper.findFactoryPageDetail(page, factoryDto);
    }

    @Override
    public void updateStatusById(String id) {
        Factory factory = factoryMapper.selectById(id);
        if (factory.getStatus() == 1) {
            factory.setStatus(2);
        } else if (factory.getStatus() == 2) {
            factory.setStatus(1);
        }

        factoryMapper.updateById(factory);
    }
}
