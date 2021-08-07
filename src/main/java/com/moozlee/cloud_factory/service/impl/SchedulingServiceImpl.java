package com.moozlee.cloud_factory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moozlee.cloud_factory.dto.SchedulingDto;
import com.moozlee.cloud_factory.mapper.IEquipmentMapper;
import com.moozlee.cloud_factory.mapper.IOrderMapper;
import com.moozlee.cloud_factory.mapper.ISchedulingMapper;
import com.moozlee.cloud_factory.po.Equipment;
import com.moozlee.cloud_factory.po.Order;
import com.moozlee.cloud_factory.po.Scheduling;
import com.moozlee.cloud_factory.service.ISchedulingService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SchedulingServiceImpl implements ISchedulingService {

    ISchedulingMapper schedulingMapper;
    IOrderMapper orderMapper;
    IEquipmentMapper equipmentMapper;

    public SchedulingServiceImpl(ISchedulingMapper schedulingMapper, IOrderMapper orderMapper, IEquipmentMapper equipmentMapper) {
        this.schedulingMapper = schedulingMapper;
        this.orderMapper = orderMapper;
        this.equipmentMapper = equipmentMapper;
    }

    @Override
    public List<SchedulingDto> getSchedulingList(String id) {
        return schedulingMapper.findSchedulingListByOrderId(id);
    }

    @Override
    public void insertScheduling(SchedulingDto dto) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Scheduling scheduling = null;
        try {
            scheduling = new Scheduling(
                UUID.randomUUID().toString(),
                dto.getId(),
                dto.getEquipmentName(),
                format.parse(dto.getStart()),
                format.parse(dto.getEnd()),
                new Date(),
                new Date(),
                0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (scheduling != null) {
            schedulingMapper.insert(scheduling);
        }

        Order order = orderMapper.selectById(dto.getId());
        order.setOrderStatus(5);
        orderMapper.updateById(order);

        Equipment equipment = equipmentMapper.selectById(dto.getEquipmentName());
        equipment.setEquipmentStatus(1);
        equipmentMapper.updateById(equipment);
    }
}
