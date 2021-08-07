package com.moozlee.cloud_factory.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.OrderDto;

public interface IOrderService {
    IPage<OrderDto> selectPage(Page<OrderDto> page, OrderDto orderDto);
    IPage<OrderDto> selectPageWithTender(Page<OrderDto> page, OrderDto orderDto);
    IPage<OrderDto> selectWinOrderPage(Page<OrderDto> page, OrderDto orderDto, String managerId);
    void takeOver(String id);
    void addOrder(OrderDto dto);
    void endProduce(String id);
    void deliver(String id);
    void accomplish(String id);
}
