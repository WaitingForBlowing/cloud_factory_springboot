package com.moozlee.cloud_factory.controller;

import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.EquipmentDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.service.IEquipmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@ApiRestController
public class EquipmentController {

    IEquipmentService equipmentService;

    public EquipmentController(IEquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/equipments/{id}")
    public Result getEquipmentList(@PathVariable("id") String id) {
        List<EquipmentDto> list = equipmentService.geEquipmentListByManagerId(id);
        return new Result().code(200).message("获取成功").data(list);
    }
}
