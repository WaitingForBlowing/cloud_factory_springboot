package com.moozlee.cloud_factory.controller;

import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.TenderDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.service.ITenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@ApiRestController
public class TenderController {

    ITenderService tenderService;

    public TenderController(ITenderService tenderService) {
        this.tenderService = tenderService;
    }

    @PutMapping("/tender")
    public Result chooseTender(String oid, String uid, Double price) {
        tenderService.chooseTender(oid, uid, price);
        return new Result().code(200).message("竞标成功");
    }

    @GetMapping("/tender/{id}")
    public Result getTenderInfo(@PathVariable("id") String id) {
        List<TenderDto> list = tenderService.getTenderInfo(id);
        return new Result().code(200).message("获取成功").data(list);
    }

    @PutMapping("/tender/select/{id}")
    public Result determineTender(@PathVariable("id") String id) {
        tenderService.determineTender(id);
        return new Result().code(200).message("选标成功");
    }
}
