package com.mn.web.service;

import com.ctc.wstx.util.StringUtil;
import com.mn.web.common.CodeMsg;
import com.mn.web.common.GlobalException;
import com.mn.web.dom.LoginVal;
import com.mn.web.dom.UserDto;
import com.mn.web.emuncl.ResponseEnum;
import org.springframework.stereotype.Service;

/**
 * 添加异常处理器之后：
 * service的处理login的业务代码是这样的：
 * 登录的记过只想知道是true还是false，其余均是抛出全局异常，交由异常处理器处理
 */
@Service
public class UserService {
    public boolean login(LoginVal loginVal){

        if(null==loginVal){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile=loginVal.getMobile();
        String password=loginVal.getPassword();
        if(StringUtil.isAllWhitespace(mobile)){
            throw new GlobalException( CodeMsg.MSG_MOBILE_NOT_EXIST);
        }
        if(StringUtil.isAllWhitespace(password)){
            throw  new GlobalException(CodeMsg.MSG_PASSWORD_ERROR);
        }
        return true;
    }

    public void getUser(){
        UserDto dto = new UserDto();
        dto.setReturnType(ResponseEnum.RETURN_TYPE_BY_PHONE);

        System.out.println(dto.getReturnType().getCode());
    }
}
