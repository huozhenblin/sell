package com.huo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Huo
 * @Description:
 * @Date: Create in 21:02 2020/4/19
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class LoggerTest {

//        private  final Logger logger = LoggerFactory.getLogger(getClass());
        @Test
    public void test1(){
            String name = "huohuo";
            String password = "123456";

            log.debug("debug...");
//            字符创拼接的方法监测变量
            log.info("info..., "+name+" ,password:"+password);
//            利用日志特殊表达式
            log.info("info..."+"name: {},password: {}",name,password);
            log.error("error...");
        }

}
