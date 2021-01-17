package com.ivan.itoutiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ivan.itoutiao.entity.Image;
import com.ivan.itoutiao.service.ImageService;
import com.ivan.itoutiao.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan
 * @date 2021/1/17 17:23
 * @Description: 图片素材controller
 */
@Api("图片素材controller")
@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {

    //文件存放的路径
    @Value("${file.path}")
    private String filePath;

    //本机ip
    @Value("${server.ip}")
    private String ip;

    //本服务使用的端口
    @Value("${server.port}")
    private String port;

    @Autowired
    ImageService imageService;

    @ApiOperation(value = "上传图片素材")
    @PostMapping("uploadImage")
    public CommonResult uploadImage(@RequestPart("image") MultipartFile file) {
        //文件的完整名称 如Ivan.jpg
        String filename = file.getOriginalFilename();
        //文件名 ——>Ivan
        String name = filename.substring(0, filename.indexOf("."));
        //文件后缀 ——>.jpg
        String suffix = filename.substring(filename.lastIndexOf("."));

        //新文件名
        String newFilename = name + "_" + new Date().getTime() + suffix;
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
            //保存数据到数据库
            Image image = new Image();
            image.setImgName(newFilename);//图片名称
            image.setImgType(file.getContentType());//图片类型
            image.setImgUrl(fileNetPath);//图片路径地址
            image.setCollect(false);//新创建的图片默认未收藏
            image.setCreateDate(new Date());//创建日期
            image.setUpdateDate(new Date());//修改日期
            //保存图片信息
            boolean save = imageService.save(image);

            if (save) {
                System.out.println("上传文件成功，附件名：" + newFilename);
                return CommonResult.success().data(fileNetPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.fail().message("上传文件失败！");
    }

    @ApiOperation(value = "分页查询图片素材信息")
    @GetMapping("getImages")
    public CommonResult getImages(@RequestParam("page")Long page,
                                  @RequestParam("pageSize")Long pageSize,
                                  @RequestParam(value = "collect", required = false)Boolean collect) {

        //创建分页查询对象
        Page<Image> pageImage = new Page<>(page,pageSize);

        //构建查询条件
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        //判断条件值是否为空，如果不为空拼接条件
        if(collect) {
            //如果是查询收藏则进行筛选;查询全部时不需要筛选
            wrapper.eq("collect",collect);
        }

        //按修改时间倒序排序
        wrapper.orderByDesc("update_date");

        imageService.page(pageImage, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("totalCount", pageImage.getTotal());
        data.put("results", pageImage.getRecords());

        return CommonResult.success().data(data);
    }

    @ApiOperation(value = "收藏/取消收藏素材")
    @PutMapping("collectImage")
    public CommonResult collectImage(@RequestParam(value = "imageId")String imageId,
                                     @RequestParam(value = "collect")Boolean collect,
                                     @RequestParam(value = "username")String username) {
        Image image = new Image();
        image.setId(imageId);
        image.setCollect(collect);
        if (collect) {
            //收藏
            image.setCollectUsername(username);
        } else {
            //取消收藏
            image.setCollectUsername(null);
        }
        //修改时间
        image.setUpdateDate(new Date());

        boolean update = imageService.updateById(image);
        if (update) {
            return CommonResult.success().message("操作成功！");
        }
        return CommonResult.fail().message("操作失败！");
    }

}
