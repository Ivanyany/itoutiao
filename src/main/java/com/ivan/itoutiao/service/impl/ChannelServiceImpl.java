package com.ivan.itoutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ivan.itoutiao.entity.Channel;
import com.ivan.itoutiao.mapper.ChannelMapper;
import com.ivan.itoutiao.service.ChannelService;
import org.springframework.stereotype.Service;

/**
 * @author Ivan
 * @date 2021/1/9 22:38
 * @Description: 频道service实现类
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {
}
