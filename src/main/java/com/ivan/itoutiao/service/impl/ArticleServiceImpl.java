package com.ivan.itoutiao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ivan.itoutiao.entity.Article;
import com.ivan.itoutiao.mapper.ArticleMapper;
import com.ivan.itoutiao.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @author Ivan
 * @date 2021/1/9 22:38
 * @Description: 文章service实现类
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
}
