package com.mn.web.common;

import com.mn.web.emuncl.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 统一异常处理器
 * java中异常分类 Exception->(IOException & RuntimeException)，详情请自行查阅文档
 * 注意有两个比较重要的注解 @ControllerAdvice、@ExceptionHandler 请自行查阅
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdviceHandler {
    /**
     * RuntimeException异常捕获并统一处理
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public ResponseJson handle(RuntimeException e) {
        log.info("RuntimeException异常或其子类异常捕获：{}", e.getMessage());

        if(e instanceof BusinessException){
            BusinessException ex=(BusinessException)e;
            log.error("{}：{}", ex.getClass(),ex.getMessage());
            return ResponseJson.error(ex.getCode(), ex.getMessage());
        }
        // 如果其他类型的RuntimeException子类，可在此处罗列
        // ……
        return ResponseJson.error(ResponseEnum.SERVER_ERROR.getCode(), e.getMessage());
    }

    /**
     * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseJson handle(Exception e) {
        log.error("系统内部服务异常：{}", e.getMessage());
        return ResponseJson.error(ResponseEnum.SERVER_ERROR);
    }
}

