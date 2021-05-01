package com.mn.web.common;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseJson exceptionHandler(HttpServletRequest request, Exception e){
        //绑定异常是需要明确提示给用户的
        if(e instanceof BindException){
            BindException exception=(BindException) e;
            List<ObjectError> errors=exception.getAllErrors();
            String msg=errors.get(0).getDefaultMessage();//获取自错误信息
            return ResponseJson.error(CodeMsg.SERVER_BIND_ERROR.fillArgs(msg).getMsg());//将具体错误信息设置到CodeMsg中返回
        }
// 其余异常简单返回为服务器异常
        return ResponseJson.error(CodeMsg.SERVER_ERROR.getMsg());

    }
}