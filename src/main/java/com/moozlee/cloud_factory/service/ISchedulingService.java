package com.moozlee.cloud_factory.service;

import com.moozlee.cloud_factory.dto.SchedulingDto;

import java.util.List;

public interface ISchedulingService {

    List<SchedulingDto> getSchedulingList(String id);
    void insertScheduling(SchedulingDto dto);
}
