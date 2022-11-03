package com.cyn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyn.entity.Usr;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
* @author G0dc
* @description 针对表【usr】的数据库操作Service
* @createDate 2022-11-01 16:30:36
*/
public interface UsrService extends IService<Usr> {
    String queryUser(Integer id);
}
