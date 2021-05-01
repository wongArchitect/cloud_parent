package com.mn.web.common;

import lombok.Data;

/**
 * 全局异常类
 */
@Data
public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg=codeMsg;

    }
}