package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moozlee.cloud_factory.dto.TenderDto;
import com.moozlee.cloud_factory.po.Tender;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITenderMapper extends BaseMapper<Tender> {

    List<TenderDto> findTenderByOrderId(@Param("id") String id);
}
