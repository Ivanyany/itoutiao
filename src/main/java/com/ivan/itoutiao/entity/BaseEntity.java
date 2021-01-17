package com.ivan.itoutiao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author Ivan
 * @date 2021/1/17 16:38
 * @Description: 实体类父类：定义公共属性
 */
@Data
public class BaseEntity {

    @ApiModelProperty(value = "创建日期")
    private Date createDate;

    @ApiModelProperty(value = "修改日期")
    private Date updateDate;

}
