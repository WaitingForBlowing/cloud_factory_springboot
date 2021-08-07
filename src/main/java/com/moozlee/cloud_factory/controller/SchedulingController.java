package com.moozlee.cloud_factory.controller;

import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.SchedulingDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.service.ISchedulingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@ApiRestController
public class SchedulingController {

    ISchedulingService schedulingService;

    public SchedulingController(ISchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @GetMapping("/schedulings/{id}")
    public Result getSchedulingList(@PathVariable("id") String id) {
        List<SchedulingDto> list = schedulingService.getSchedulingList(id);
        return new Result().code(200).message("获取成功").data(list);
    }

    @PostMapping("/scheduling")
    public Result addScheduling(SchedulingDto dto) {
        schedulingService.insertScheduling(dto);
        return new Result().code(200).message("新增成功");
    }

}
