package com.mn.web.common;

import com.mn.web.anonation.inf.ResponseJsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ResponseJsonFormatHandler implements ResponseBodyAdvice<Object> {
    /**
     * 标记位
     */
    public static final String RESPONSE_FORMAT_FLAG = "RESPONSE_JSON_FLAG";
    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        // 判断请求是否需要包装，默认true
        // 判断请求是否包含了注解标记
        ServletRequestAttributes sra =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        ResponseJsonFormat responseFlag =
                (ResponseJsonFormat) request.getAttribute(RESPONSE_FORMAT_FLAG);
        return null != responseFlag;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("进入返回体重写过程,封装code msg data...");
        if (null == o) {
            return ResponseJson.ok(); 		//成功执行但无数据返回，返回code，msg
        } else if (o instanceof ResponseJson) {
            return o;   					//已经在controller封装完成，直接返回
        }
        return ResponseJson.ok(o);          // 尚未包装的成功数据，此处封装code msg data
    }
}
