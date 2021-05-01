package com.mn.web.emuncl;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回码封装，用枚举类实现
 *
 * @author jockeys
 * @since 2020/3/11
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum {
    /**
     * 成功状态码
     */
    SUCCESS("0", "success"),

    /**
     * 系统异常相关错误
     */
    SERVER_ERROR("-1", "未知异常，请联系管理员"),
    SYSTEM_ERROR("system-err", "系统内部错误，请联系管理员..."),
    NETWORK_ERROR("network-err", "网络故障，请检查网络连接..."),
    BUSINESS_ERROR("business-err", "业务处理中，请稍后重试..."),

    /**
     * 通用的校验错误
     */
    ILLEGAL_ARGS_ERROR("args-illegal-err", "非法参数错误"),
    ARGS_PASSWORD_WRONG("password-format-err", "密码格式不正确"),
    ARGS_PHONE_WRONG("phone-format-err", "手机格式不正确"),
    ;

    private String code;
    private String message;
}
