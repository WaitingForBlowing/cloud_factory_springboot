package com.moozlee.cloud_factory.service;

import com.moozlee.cloud_factory.dto.TenderDto;

import java.util.List;

public interface ITenderService {

    void chooseTender(String oid, String uid, Double price);

    List<TenderDto> getTenderInfo(String id);

    void determineTender(String id);
}
