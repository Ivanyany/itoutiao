package com.ivan.itoutiao.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivan.itoutiao.entity.User;
import com.ivan.itoutiao.service.UserService;
import org.springframework.util.StringUtils;

/**
 * @author Ivan
 * @Title: IvanTool 公工方法
 * @date 2021/1/2916:17
 */
public class IvanTool {

    //根据token查询用户
    public static User getUserByToken(UserService userService, String token) {
        if (!StringUtils.isEmpty(token)){
            //查询条件
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(token)) {
                wrapper.eq("token",token);
            }
            return userService.getOne(wrapper);
        }
        return null;
    }

}
