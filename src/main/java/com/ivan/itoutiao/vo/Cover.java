package com.ivan.itoutiao.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan
 * @date 2021/1/10 14:52
 * @Description: 封面
 */
@Data
public class Cover {

    @ApiModelProperty(value = "封面类型(1单图  3三图  0无图  -1自动)")
    private Integer type;

    @ApiModelProperty(value = "标题")
    private List<String> images;

}
