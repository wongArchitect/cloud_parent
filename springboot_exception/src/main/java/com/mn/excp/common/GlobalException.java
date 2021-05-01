package com.mn.excp.common;

import lombok.Data;

/**
 * 全局异常类
 */
@Data
public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;

    public static GlobalException SERVER_ERROR = new GlobalException(CodeMsg.SERVER_ERROR);

    public GlobalException(CodeMsg codeMsg){
        super(codeMsg.toString());
        this.codeMsg=codeMsg;

    }
}