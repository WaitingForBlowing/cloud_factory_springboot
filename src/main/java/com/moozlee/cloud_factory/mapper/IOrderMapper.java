package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.OrderDto;
import com.moozlee.cloud_factory.po.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderMapper extends BaseMapper<Order> {
    IPage<OrderDto> findOrderPageDetail(Page<OrderDto> page, @Param("order") OrderDto orderDto);
    IPage<OrderDto> findOrderPageDetailWithTender(Page<OrderDto> page, @Param("order") OrderDto orderDto);
    IPage<OrderDto> findWinOrderPageDetail(Page<OrderDto> page, @Param("order") OrderDto orderDto, @Param("id") String factoryId);
    Order selectOrderById(@Param("id") String id);
}
