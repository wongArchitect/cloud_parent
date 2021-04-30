package com.mn.annotate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 注解-业务处理-类
 * 注解搜集信息后，所增强的功能实际实现代码。
 * 做业务处理，如，权限校验、属性读取、角色和数据分配等。
 */
@Service
@Slf4j
public class Decrypt {

    //读取controller方法中的参数列表，读取属性文件的属性值。
    public void check(String token, String checkUrl, String keyUrl, String tokenScope) {
        log.info("checkUrl : " + checkUrl);
        log.info("keyUrl : " + keyUrl);
        log.info("tokenScope : " + tokenScope);
        log.info("token : " + token);

    }
}
