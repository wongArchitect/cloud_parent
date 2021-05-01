package com.mn.excp.dom;

import com.mn.excp.common.CodeMsg;
import com.mn.excp.common.GlobalException;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回result
 */
@Data
public class ZingResult<T> implements Serializable {

    private int code;
    private String msg;
    private T data;


    private ZingResult() {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
    }

    private ZingResult(T data) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getMsg();
        this.data = data;
    }


    private ZingResult(GlobalException exceptionEnum) {
        this.code = exceptionEnum.getCodeMsg().getCode();
        this.msg = exceptionEnum.getCodeMsg().getMsg();
    }

    public static ZingResult success() {
        return new ZingResult();
    }

    public static <T> ZingResult<T> success(T data) {
        return new ZingResult<>(data);
    }

    public static <T> ZingResult<T> error(GlobalException exceptionEnum) {
        return new ZingResult<>(exceptionEnum);
    }

}