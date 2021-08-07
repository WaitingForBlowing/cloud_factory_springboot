package com.moozlee.cloud_factory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moozlee.cloud_factory.po.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuMapper extends BaseMapper<Menu> {

    List<Menu> findByRoleId(@Param("id") Integer id);
}
