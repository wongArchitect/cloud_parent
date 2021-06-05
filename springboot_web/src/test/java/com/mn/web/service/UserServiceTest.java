package com.mn.web.service;

import com.mn.web.dom.UserDto;
import com.mn.web.emuncl.ResponseEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void getUser() {
        UserDto dto = new UserDto();
        dto.setReturnType(ResponseEnum.RETURN_TYPE_BY_PHONE);

        System.out.println(dto.getReturnType());
    }
}