package com.mn.excp.common;

import com.mn.excp.dom.ZingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.validation.BindException;
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ZingResult<CodeMsg> exceptionHandler(HttpServletRequest request, Exception e){
        //绑定异常是需要明确提示给用户的
        if(e instanceof BindException){
            BindException exception=(BindException) e;
            List<ObjectError> errors=exception.getAllErrors();
            String msg=errors.get(0).getDefaultMessage();//获取自错误信息
            return ZingResult.success(CodeMsg.SERVER_BIND_ERROR.fillArgs(msg));//将具体错误信息设置到CodeMsg中返回
        }
// 其余异常简单返回为服务器异常
        return ZingResult.error(GlobalException.SERVER_ERROR);

    }
}