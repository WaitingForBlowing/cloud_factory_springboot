package com.moozlee.cloud_factory.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moozlee.cloud_factory.dto.TenderDto;
import com.moozlee.cloud_factory.mapper.IFactoryMapper;
import com.moozlee.cloud_factory.mapper.IOrderMapper;
import com.moozlee.cloud_factory.mapper.ITenderMapper;
import com.moozlee.cloud_factory.mapper.IUserMapper;
import com.moozlee.cloud_factory.po.Factory;
import com.moozlee.cloud_factory.po.Order;
import com.moozlee.cloud_factory.po.Tender;
import com.moozlee.cloud_factory.service.ITenderService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class TenderServiceImpl implements ITenderService {

    IUserMapper userMapper;
    ITenderMapper tenderMapper;
    IOrderMapper orderMapper;
    IFactoryMapper factoryMapper;

    public TenderServiceImpl(IUserMapper userMapper, ITenderMapper tenderMapper, IOrderMapper orderMapper, IFactoryMapper factoryMapper) {
        this.userMapper = userMapper;
        this.tenderMapper = tenderMapper;
        this.orderMapper = orderMapper;
        this.factoryMapper = factoryMapper;
    }

    @Override
    public void chooseTender(String oid, String uid, Double price) {
        QueryWrapper<Tender> wrapper = new QueryWrapper<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("order_id", oid);
        map.put("manager_id", uid);
        wrapper.allEq(map);
        Tender t = tenderMapper.selectOne(wrapper);
        if (t != null) {
            t.setPrice(price);
            tenderMapper.updateById(t);
        } else {
            Tender tender = new Tender(UUID.randomUUID().toString(), oid, null, uid, null, price, 0, new Date(), new Date(), 0);
            tenderMapper.insert(tender);
        }
    }

    @Override
    public List<TenderDto> getTenderInfo(String id) {
        return tenderMapper.findTenderByOrderId(id);
    }

    @Override
    public void determineTender(String id) {
        Tender tender = tenderMapper.selectById(id);
        tender.setStatus(1);
        tenderMapper.updateById(tender);
        Order order = orderMapper.selectOrderById(tender.getOrderId());
        order.setOrderStatus(4);

        QueryWrapper<Factory> w = new QueryWrapper<>();
        w.eq("manager_id", tender.getManagerId());
        Factory factory = factoryMapper.selectOne(w);
        order.setFactoryId(factory.getId());

        orderMapper.updateById(order);

    }
}
