package com.mn.trans.service;

import java.util.List;
import java.util.Map;

public interface  AccountService {
     //转账
     public void transfer(int outter,int inner,Integer money);

     public List<Map<String, Object>> prod();

     public List<Map<String, Object>> dev();
}
