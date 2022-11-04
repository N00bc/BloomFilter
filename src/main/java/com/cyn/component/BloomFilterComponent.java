package com.cyn.component;

import com.cyn.mapper.UsrMapper;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author G0dc
 * @description:
 * @date 2022/11/3 14:18
 */
@Component
public class BloomFilterComponent {
    /**
     * 参数1 Funnel funnel：BloomFilter元素值类型
     * 参数2 int expectedInsertions：BloomFilter中预期加入 key 的数量，此处演示所以直接写死。
     * volatile：为了后续并发新增 key 的全局可见性
     */
    private static volatile BloomFilter<String> USER = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 1000);
    private static volatile BloomFilter<String> SALER = BloomFilter.create(Funnels.stringFunnel(StandardCharsets.UTF_8), 1000);

    @Autowired
    private UsrMapper usrMapper;

    /**
     * 将数据库的用户id存入BloomFilter
     */
    @PostConstruct
    public void init() {
        List<Integer> list = usrMapper.selectId();
        list.forEach(id -> this.USER.put(id.toString()));
    }

    public boolean isUserExist(String id) {
        return USER.mightContain(id);
    }
    // 如果有新增用户等的需求
}
