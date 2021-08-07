package com.moozlee.cloud_factory.service;

import com.moozlee.cloud_factory.dto.EquipmentDto;

import java.util.List;

public interface IEquipmentService {
    List<EquipmentDto> geEquipmentListByManagerId(String id);
}
