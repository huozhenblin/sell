package com.huo.until;

import java.util.Random;

/**
 * @Author: Huo
 * @Description:生成唯一主键 时间加随机数
 * @Date: Create in 18:02 2020/4/28
 */
public class KeyUtil {
    public static synchronized String gunUniqueKey(){
        Random random = new Random();

        Integer a = random.nextInt(900000) + 100000;
        return System.currentTimeMillis()+String.valueOf(a);
    }
}
