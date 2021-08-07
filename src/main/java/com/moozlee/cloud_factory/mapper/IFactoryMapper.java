package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.FactoryDto;
import com.moozlee.cloud_factory.po.Factory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFactoryMapper extends BaseMapper<Factory> {

    IPage<FactoryDto> findFactoryPageDetail(Page<FactoryDto> page, @Param("factory") FactoryDto factoryDto);
}
