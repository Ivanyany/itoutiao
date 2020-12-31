package com.ivan.itoutiao.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan
 * @date 2020/12/31 12:13
 * @Description: 通用结果返回类
 */
@Data
public class CommonResult {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    //把构造方法私有
    private CommonResult() {}

    /**
     * @author Ivan
     * @date 2020/7/19 22:31
     * @Description: 成功静态方法
     */
    public static CommonResult success() {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("操作成功!");
        return result;
    }

    /**
     * @author Ivan
     * @date 2020/7/19 22:31
     * @Description: 失败静态方法
     */
    public static CommonResult fail() {
        CommonResult result = new CommonResult();
        result.setCode(ResultCode.ERROR);
        result.setMessage("操作失败!");
        return result;
    }

    /**
     * @author Ivan
     * @date 2020/7/19 22:31
     * @Description: 自定义返回状态码
     */
    public CommonResult code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * @author Ivan
     * @date 2020/7/19 22:31
     * @Description: 自定义返回消息
     */
    public CommonResult message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * @author Ivan
     * @date 2020/7/19 22:31
     * @Description: 自定义返回数据
     */
    public CommonResult data(Object data){
        this.setData(data);
        return this;
    }

}
