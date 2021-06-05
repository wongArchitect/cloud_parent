package com.mn.web.dom;

import lombok.Data;

@Data
public class UserDto extends BaseDto{

   public String mobile;

   public String password;

   private String sex;
}
