package com.cyn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyn.entity.Usr;

import java.util.List;

/**
 * @author G0dc
 * @description 针对表【usr】的数据库操作Mapper
 * @createDate 2022-11-01 16:30:35
 * @Entity com/cyn.entity.Usr
 */
public interface UsrMapper extends BaseMapper<Usr> {
    List<Integer> selectId();
}




