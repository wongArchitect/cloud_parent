package com.mn.web.common;

import com.mn.web.emuncl.ResponseEnum;

/**
 * 自定义异常的基类，继承自运行时异常
 *
 * @author jockeys
 * @since 2020/2/2
 */
public class BusinessException extends RuntimeException {

    private static final String DEFAULT_CODE_VALUE="-1";
    private String code = DEFAULT_CODE_VALUE;

    /**
     * 构造器 (with code & msg )
     *
     * @param responseEnum 响应码的枚举实例
     */
    public BusinessException(ResponseEnum responseEnum) {
        this(responseEnum.getMessage());
        this.code = responseEnum.getCode();
    }

    /**
     * 构造器 (only msg)
     *
     * @param message 错误信息(msg)
     */
    public BusinessException(String message) {
        super(message);
    }

    public String getCode() {
        return this.code;
    }

    public BusinessException setCode(String code) {
        this.code = code;
        return this;
    }
}

