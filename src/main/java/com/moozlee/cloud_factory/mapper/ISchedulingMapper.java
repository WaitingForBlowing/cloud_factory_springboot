package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moozlee.cloud_factory.dto.SchedulingDto;
import com.moozlee.cloud_factory.po.Scheduling;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISchedulingMapper extends BaseMapper<Scheduling> {

    List<SchedulingDto> findSchedulingListByOrderId(@Param("id") String id);
}
