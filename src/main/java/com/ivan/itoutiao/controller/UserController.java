package com.ivan.itoutiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivan.itoutiao.entity.User;
import com.ivan.itoutiao.service.UserService;
import com.ivan.itoutiao.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ivan
 * @date 2020/12/31 13:03
 * @Description: 用户controller
 */
@Api("用户controller")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;


    /**
     * @author Ivan
     * @date 2020/12/31 12:27
     * @Description: 用户登录
     */
    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public CommonResult login(@RequestBody User user) {
        //查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        String mobile = user.getMobile();
        String code = user.getCode();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(mobile)) {
            wrapper.eq("mobile",mobile);
        }
        if(!StringUtils.isEmpty(code)) {
            wrapper.eq("code",code);
        }
        User baseUser = userService.getOne(wrapper);
        if (baseUser!=null){
            return CommonResult.success().data(baseUser);
        }
        return CommonResult.fail().message("用户名或密码错误！");
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("profile")
    public CommonResult profile(@RequestHeader("Authorization") String Authorization) {
        if (!StringUtils.isEmpty(Authorization) && Authorization.contains(" ")){
            String token = Authorization.split(" ")[1];
            //查询条件
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(token)) {
                wrapper.eq("token",token);
            }
            User user = userService.getOne(wrapper);
            if (user!=null){
                return CommonResult.success().data(user);
            }
        }
        return CommonResult.fail().message("未查询到用户信息！");
    }
}
