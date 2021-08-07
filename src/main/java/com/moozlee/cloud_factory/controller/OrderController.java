package com.moozlee.cloud_factory.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moozlee.cloud_factory.annotation.ApiRestController;
import com.moozlee.cloud_factory.dto.OrderDto;
import com.moozlee.cloud_factory.dto.QueryDto;
import com.moozlee.cloud_factory.po.Result;
import com.moozlee.cloud_factory.service.IOrderService;
import org.springframework.web.bind.annotation.*;

@ApiRestController
public class OrderController {

    ObjectMapper mapper;
    IOrderService orderService;

    public OrderController(ObjectMapper mapper, IOrderService orderService) {
        this.mapper = mapper;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public Result getOrderList(QueryDto query) {
        OrderDto order = new OrderDto();
        try {
            order = mapper.readValue(query.getQuery(), OrderDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        IPage<OrderDto> page = orderService.selectPage(new Page<>(query.getPageNumber(), query.getPageSize()), order);
        return new Result().code(200).message("获取成功").data(page);
    }

    @GetMapping("/orders/tender")
    public Result getTenderWithOrderList(QueryDto query) {
        OrderDto order = new OrderDto();
        try {
            order = mapper.readValue(query.getQuery(), OrderDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        IPage<OrderDto> page = orderService.selectPageWithTender(new Page<>(query.getPageNumber(), query.getPageSize()), order);
        System.out.println(page.getRecords());
        return new Result().code(200).message("获取成功").data(page);
    }

    @GetMapping("/orders/win")
    public Result getWinOrderList(QueryDto query) {
        OrderDto order = new OrderDto();
        try {
            System.out.println(query.getQuery());
            order = mapper.readValue(query.getQuery(), OrderDto.class);
            System.out.println(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String managerId = order.getId();
        order.setId(null);
        IPage<OrderDto> page = orderService.selectWinOrderPage(new Page<>(query.getPageNumber(), query.getPageSize()), order, managerId);
        System.out.println(page.getRecords());
        return new Result().code(200).message("获取成功").data(page);
    }

    @PutMapping("/order/{id}/over")
    public Result takeOverOrder(@PathVariable("id") String id) {
        orderService.takeOver(id);
        return new Result().code(200).message("修改成功");
    }

    @PutMapping("/order/{id}/end")
    public Result endProduce(@PathVariable("id") String id) {
        orderService.endProduce(id);
        return new Result().code(200).message("修改成功");
    }

    @PutMapping("/order/{id}/deliver")
    public Result deliver(@PathVariable("id") String id) {
        orderService.deliver(id);
        return new Result().code(200).message("修改成功");
    }

    @PutMapping("/order/{id}/accomplish")
    public Result accomplish(@PathVariable("id") String id) {
        orderService.accomplish(id);
        return new Result().code(200).message("修改成功");
    }

    @PostMapping("/order")
    public Result addOrder(OrderDto dto) {
        orderService.addOrder(dto);
        return new Result().code(200).message("新增成功");
    }
}
