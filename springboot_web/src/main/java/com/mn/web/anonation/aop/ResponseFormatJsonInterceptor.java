package com.mn.web.anonation.aop;

import com.mn.web.anonation.inf.ResponseFormat;
import com.mn.web.anonation.inf.ResponseJsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 响应格式-自定义注解拦截处理
 */
@Slf4j
@Component
public class ResponseFormatJsonInterceptor implements HandlerInterceptor {
    public static final String RESPONSE_FORMAT_FLAG = "RESPONSE_JSON_FLAG";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 请求里面添加注解@ResponseJsonFormat的标志，为了返回响应时拦截进行处理
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(ResponseFormat.class)) {
                request.setAttribute(RESPONSE_FORMAT_FLAG,
                        clazz.getAnnotation(ResponseJsonFormat.class));
            } else if (method.isAnnotationPresent(ResponseFormat.class)) {
                request.setAttribute(RESPONSE_FORMAT_FLAG,
                        method.getAnnotation(ResponseJsonFormat.class));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
