package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moozlee.cloud_factory.dto.EquipmentDto;
import com.moozlee.cloud_factory.po.Equipment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEquipmentMapper extends BaseMapper<Equipment> {

    List<EquipmentDto> findEquipmentListByManagerId(@Param("id") String id);
}
