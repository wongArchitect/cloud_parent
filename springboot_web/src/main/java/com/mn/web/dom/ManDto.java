package com.mn.web.dom;

import com.mn.web.emuncl.PeopleEnum;
import lombok.Data;

@Data
public class ManDto {

//   此实体类的专属属性变量，所以以静态变量放在此类中
   private final static PeopleEnum man = PeopleEnum.PEOPLEENUM_MAN

   public String mobile;

   public String password;

   private String sex;
}
