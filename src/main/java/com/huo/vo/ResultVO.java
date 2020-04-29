package com.huo.vo;

import lombok.Data;

/**
 * @Author: Huo
 * @Description:http返回的最外层对象
 * @Date: Create in 13:17 2020/4/23
 */
@Data
public class ResultVO<T> {

//    错误码
    private Integer code;
//提示信息
    private String msg;
//具体内容
    private T data;



}
