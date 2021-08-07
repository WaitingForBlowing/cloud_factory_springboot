package com.moozlee.cloud_factory.service.impl;

import com.moozlee.cloud_factory.dto.EquipmentDto;
import com.moozlee.cloud_factory.mapper.IEquipmentMapper;
import com.moozlee.cloud_factory.service.IEquipmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentServiceImpl implements IEquipmentService {

    IEquipmentMapper equipmentMapper;

    public EquipmentServiceImpl(IEquipmentMapper equipmentMapper) {
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public List<EquipmentDto> geEquipmentListByManagerId(String id) {
        return equipmentMapper.findEquipmentListByManagerId(id);
    }
}
