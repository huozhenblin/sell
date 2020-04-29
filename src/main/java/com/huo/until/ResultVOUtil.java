package com.huo.until;

import com.huo.vo.ResultVO;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 18:15 2020/4/23
 */
public class ResultVOUtil {
//    操作成功
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("查询成功");
        return resultVO;

    }
//    操作成功返回为空
    public static ResultVO success(){
        return success(null);
    }
//    查询错误
    public static ResultVO error(Integer status,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
