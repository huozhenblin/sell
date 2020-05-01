package com.huo.sellException;

import com.huo.enums.ResultEnmu;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 17:43 2020/4/28
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnmu resultEnmu) {
        super(resultEnmu.getMessage());
        this.code = resultEnmu.getCode();
    }

    public SellException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
