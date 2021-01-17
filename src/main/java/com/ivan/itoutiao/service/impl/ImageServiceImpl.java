package com.ivan.itoutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ivan.itoutiao.entity.Image;
import com.ivan.itoutiao.mapper.ImageMapper;
import com.ivan.itoutiao.service.ImageService;
import org.springframework.stereotype.Service;

/**
 * @author Ivan
 * @date 2021/1/9 22:38
 * @Description: 图片service实现类
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {
}
