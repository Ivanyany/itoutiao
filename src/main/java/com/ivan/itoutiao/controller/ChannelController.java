package com.ivan.itoutiao.controller;

import com.ivan.itoutiao.entity.Channel;
import com.ivan.itoutiao.service.ChannelService;
import com.ivan.itoutiao.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ivan
 * @date 2021/1/9 22:36
 * @Description: 频道controller
 */
@Api("频道controller")
@RestController
@RequestMapping("/channel")
@CrossOrigin
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @ApiOperation(value = "查询所有频道信息")
    @GetMapping("getChannels")
    public CommonResult getChannels() {

        List<Channel> list = channelService.list(null);

        if (list!=null) {
            return CommonResult.success().data(list);
        }
        return CommonResult.fail().message("未查询到频道信息！");
    }

}
