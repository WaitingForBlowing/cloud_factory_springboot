package com.moozlee.cloud_factory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moozlee.cloud_factory.dto.OrderDto;
import com.moozlee.cloud_factory.mapper.IEquipmentMapper;
import com.moozlee.cloud_factory.mapper.IFactoryMapper;
import com.moozlee.cloud_factory.mapper.IOrderMapper;
import com.moozlee.cloud_factory.mapper.ISchedulingMapper;
import com.moozlee.cloud_factory.po.Equipment;
import com.moozlee.cloud_factory.po.Factory;
import com.moozlee.cloud_factory.po.Order;
import com.moozlee.cloud_factory.po.Scheduling;
import com.moozlee.cloud_factory.service.IOrderService;
import com.moozlee.cloud_factory.service.ISchedulingService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {

    IOrderMapper orderMapper;
    IFactoryMapper factoryMapper;
    IEquipmentMapper equipmentMapper;
    ISchedulingMapper schedulingMapper;

    public OrderServiceImpl(IOrderMapper orderMapper, IFactoryMapper factoryMapper, IEquipmentMapper equipmentMapper, ISchedulingMapper schedulingMapper) {
        this.orderMapper = orderMapper;
        this.factoryMapper = factoryMapper;
        this.equipmentMapper = equipmentMapper;
        this.schedulingMapper = schedulingMapper;
    }

    @Override
    public IPage<OrderDto> selectPage(Page<OrderDto> page, OrderDto orderDto) {
        return orderMapper.findOrderPageDetail(page, orderDto);
    }

    @Override
    public IPage<OrderDto> selectPageWithTender(Page<OrderDto> page, OrderDto orderDto) {
        return orderMapper.findOrderPageDetailWithTender(page, orderDto);
    }

    @Override
    public IPage<OrderDto> selectWinOrderPage(Page<OrderDto> page, OrderDto orderDto, String managerId) {
        QueryWrapper<Factory> w = new QueryWrapper<>();
        w.eq("manager_id", managerId);
        Factory factory = factoryMapper.selectOne(w);
        System.out.println(factory);
        return orderMapper.findWinOrderPageDetail(page, orderDto, factory.getId());
    }

    @Override
    public void takeOver(String id) {
        Order order = orderMapper.selectById(id);
        order.setOrderStatus(8);
        orderMapper.updateById(order);
    }

    @Override
    public void addOrder(OrderDto dto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order order = null;
        try {
            order = new Order(UUID.randomUUID().toString(), dto.getProductName(), null, dto.getProductAmount(), format.parse(dto.getAccomplishDeadline()),
                format.parse(dto.getTenderDeadline()), 2, dto.getConsigneeName(), null, null, null, new Date(), new Date(), 0);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (order != null) {
            orderMapper.insert(order);
        }
    }

    @Override
    public void endProduce(String id) {
        Order order = orderMapper.selectById(id);

        QueryWrapper<Scheduling> wrapper = new QueryWrapper<>();
        wrapper.eq("order_id", id);
        Scheduling scheduling = schedulingMapper.selectOne(wrapper);

        Equipment equipment = equipmentMapper.selectById(scheduling.getEquipmentId());
        equipment.setEquipmentStatus(2);
        equipmentMapper.updateById(equipment);
        order.setOrderStatus(6);
        orderMapper.updateById(order);
    }

    @Override
    public void deliver(String id) {
        Order order = orderMapper.selectById(id);
        order.setOrderStatus(7);
        orderMapper.updateById(order);
    }

    @Override
    public void accomplish(String id) {
        Order order = orderMapper.selectById(id);
        order.setOrderStatus(9);
        orderMapper.updateById(order);
    }
}
