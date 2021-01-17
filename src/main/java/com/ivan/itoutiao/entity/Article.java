package com.ivan.itoutiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ivan.itoutiao.vo.Cover;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Ivan
 * @date 2021/1/9 22:32
 * @Description: 文章表
 */
@Data
public class Article extends BaseEntity {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文章状态 0-草稿，1-待审核，2-审核通过，3-审核失败，4-已删除")
    private Integer status;

    @ApiModelProperty(value = "发布日期")
    private Date publishDate;

    @ApiModelProperty(value = "频道id-外键")
    private String channelId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "评论状态，response_type=comment时返回(1开启评论  0关闭评论)")
    private Boolean commentStatus;

    @ApiModelProperty(value = "总评论数，response_type=comment时返回")
    private Integer totalCommentCount;

    @ApiModelProperty(value = "粉丝评论数，response_type=comment时返回")
    private Integer fansCommentCount;

    @ApiModelProperty(value = "评论数，response_type=statistic时返回")
    private Integer commentCount;

    @ApiModelProperty(value = "阅读数，response_type=statistic时返回")
    private Integer readCount;

    @ApiModelProperty(value = "点赞数，response_type=statistic时返回")
    private Integer likeCount;

    @ApiModelProperty(value = "转发数，response_type=statistic时返回")
    private Integer repostCount;

    @ApiModelProperty(value = "收藏数，response_type=statistic时返回")
    private Integer collectCount;

    @ApiModelProperty(value = "封面")
    private transient Cover cover = new Cover();

}
