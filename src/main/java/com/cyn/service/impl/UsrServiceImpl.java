package com.cyn.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyn.annotation.BloomFilterAnno;
import com.cyn.constant.SystemConstant;
import com.cyn.entity.Usr;
import com.cyn.mapper.UsrMapper;
import com.cyn.service.UsrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author G0dc
 * @description 针对表【usr】的数据库操作Service实现
 * @createDate 2022-11-01 16:30:36
 */
@Service
@Transactional
public class UsrServiceImpl extends ServiceImpl<UsrMapper, Usr> implements UsrService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @BloomFilterAnno(key = SystemConstant.USER)
    public String queryUser(Integer id) {
        // 拼接 Redis 的key
        String key = SystemConstant.USER + id;
        // 先从 Redis 中查找用户是否存在
        String user = stringRedisTemplate.opsForValue().get(key);
        // 不存在？去数据库中查找
        if (user == null || user.length() == 0) {
            Usr query = getById(id);
            // 用户不存在抛出异常
            if (query == null) {
                throw new RuntimeException("用户不存在");
            }
            user = JSON.toJSONString(query);
            // 将用户存入 Redis
            stringRedisTemplate.opsForValue().set(key, user, 30, TimeUnit.MINUTES);
        } else {
            // 刷新有效时间
            stringRedisTemplate.expire(key, 30, TimeUnit.MINUTES);
        }
        return user;
    }

}




