package com.ivan.itoutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ivan.itoutiao.entity.User;
import com.ivan.itoutiao.mapper.UserMapper;
import com.ivan.itoutiao.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Ivan
 * @date 2020/12/31 12:23
 * @Description: 用户service实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
