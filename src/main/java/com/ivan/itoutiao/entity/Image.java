package com.ivan.itoutiao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ivan.itoutiao.vo.Cover;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Ivan
 * @date 2021/1/9 22:32
 * @Description: 图片素材
 */
@Data
public class Image extends BaseEntity {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "图片名称")
    private String imgName;

    @ApiModelProperty(value = "图片类型")
    private String imgType;

    @ApiModelProperty(value = "图片路径")
    private String imgUrl;

    @ApiModelProperty(value = "收藏状态（1收藏 0未收藏）")
    private Boolean collect;

    @ApiModelProperty(value = "收藏人姓名")
    private String collectUsername;

}
