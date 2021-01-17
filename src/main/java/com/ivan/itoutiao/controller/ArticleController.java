package com.ivan.itoutiao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ivan.itoutiao.entity.Article;
import com.ivan.itoutiao.service.ArticleService;
import com.ivan.itoutiao.utils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan
 * @date 2021/1/9 22:36
 * @Description: 文章controller
 */
@Api("文章controller")
@RestController
@RequestMapping("/article")
@CrossOrigin
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @ApiOperation(value = "分页查询文章列表信息")
    @GetMapping("getArticles")
    public CommonResult getArticles(@RequestParam("page")Long page,
                                @RequestParam("pageSize")Long pageSize,
                                @RequestParam(value = "status", required = false)Integer status,
                                @RequestParam(value = "channelId", required = false)String channelId,
                                @RequestParam(value = "beginDate", required = false)String beginDate,
                                @RequestParam(value = "endDate",required = false)String endDate) {

        //创建分页查询对象
        Page<Article> pageArticle = new Page<>(page,pageSize);

        //构建查询条件
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        //判断条件值是否为空，如果不为空拼接条件
        if(status != null) {
            wrapper.like("status",status);
        }
        if(!StringUtils.isEmpty(channelId)) {
            wrapper.eq("channel_id",channelId);
        }
        if(!StringUtils.isEmpty(beginDate)) {
            wrapper.ge("publish_date",beginDate + " 00:00:00");
        }
        if(!StringUtils.isEmpty(endDate)) {
            wrapper.le("publish_date",endDate + " 23:59:59");
        }
        //按修改时间倒序排序
        wrapper.orderByDesc("update_date");

        articleService.page(pageArticle, wrapper);

        Map<String, Object> data = new HashMap<>();
        data.put("totalCount", pageArticle.getTotal());
        data.put("results", pageArticle.getRecords());

        return CommonResult.success().data(data);
    }

    @ApiOperation(value = "根据id删除文章")
    @DeleteMapping("deleteArticle/{articleId}")
    public CommonResult deleteArticle(@PathVariable("articleId")String articleId) {
        boolean remove = articleService.removeById(articleId);
        if (remove) {
            return CommonResult.success().message("删除成功！");
        }
        return CommonResult.fail().message("删除失败！");
    }

    @ApiOperation(value = "新增文章")
    @PostMapping("addArticle")
    public CommonResult addArticle(@RequestBody Article article,
                                   @RequestParam(value = "draft") Boolean draft) {
        if (draft) {
            article.setStatus(0); // 0-草稿
        } else {
            article.setStatus(2); // 2-审核通过(此时为发布状态)
            article.setPublishDate(new Date());
        }
        //创建时间
        article.setCreateDate(new Date());
        //修改时间
        article.setUpdateDate(new Date());
        boolean save = articleService.save(article);
        if (save) {
            return CommonResult.success().message("新增成功！");
        }
        return CommonResult.fail().message("新增失败！");
    }

    @ApiOperation(value = "根据id查询文章")
    @GetMapping("getArticleById/{articleId}")
    public CommonResult getArticleById(@PathVariable("articleId")String articleId) {
        Article article = articleService.getById(articleId);
        if (article != null) {
            return CommonResult.success().data(article);
        }
        return CommonResult.fail().message("查询失败！");
    }

    @ApiOperation(value = "编辑文章")
    @PutMapping("updateArticle")
    public CommonResult updateArticleById(@RequestBody Article article,
                                          @RequestParam(value = "draft") Boolean draft) {
        if (draft) {
            article.setStatus(0); // 0-草稿
        } else {
            article.setStatus(2); // 2-审核通过(此时为发布状态)
            article.setPublishDate(new Date());
        }
        //修改时间
        article.setUpdateDate(new Date());
        boolean update = articleService.updateById(article);
        if (update) {
            return CommonResult.success().message("修改成功！");
        }
        return CommonResult.fail().message("修改失败！");
    }

    @ApiOperation(value = "修改文章评论状态")
    @PutMapping("updateCommentsStatus")
    public CommonResult updateCommentsStatus(@RequestParam(value = "articleId") String articleId,
                                             @RequestParam(value = "allowComment") Boolean allowComment) {
        Article article = new Article();
        article.setId(articleId);
        article.setCommentStatus(allowComment);

        boolean update = articleService.updateById(article);
        if (update) {
            return CommonResult.success().message("修改成功！");
        }
        return CommonResult.fail().message("修改失败！");
    }

}
