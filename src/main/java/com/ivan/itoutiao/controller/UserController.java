package com.ivan.itoutiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ivan.itoutiao.entity.User;
import com.ivan.itoutiao.service.UserService;
import com.ivan.itoutiao.utils.CommonResult;
import com.ivan.itoutiao.utils.IvanTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

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

    //文件存放的路径
    @Value("${file.path}")
    private String filePath;

    //本服务使用的ip
    @Value("${server.ip}")
    private String ip;

    //本服务使用的端口
    @Value("${server.port}")
    private String port;

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
        if (!StringUtils.isEmpty(Authorization) && Authorization.contains("Bearer")){
            String token = Authorization.split(" ")[1];
            User user = IvanTool.getUserByToken(userService, token);
            if (user!=null){
                return CommonResult.success().data(user);
            }
        }
        return CommonResult.fail().message("未查询到用户信息！");
    }

    @ApiOperation(value = "修改用户信息")
    @PatchMapping("updateProfile")
    public CommonResult updateProfile(@RequestBody User user) {
        boolean update = userService.updateById(user);
        if (update) {
            return CommonResult.success().message("修改成功！");
        }
        return CommonResult.fail().message("修改失败！");
    }

    @ApiOperation(value = "修改用户头像")
    @PostMapping("updatePhoto")
    public CommonResult updatePhoto(@RequestPart("photo") MultipartFile file) {

        //新文件名
        String newFilename =  new Date().getTime() + ".png";
        //目标文件
        File descFile = new File(filePath  + newFilename);

        //判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            descFile.getParentFile().mkdirs();
        }

        String fileNetPath = "http://" + ip + ":" + port + "/file/" + newFilename;

        try {
            //将数据写入磁盘
            file.transferTo(descFile);
            return CommonResult.success().data(fileNetPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.fail().message("上传文件失败！");
    }

}
