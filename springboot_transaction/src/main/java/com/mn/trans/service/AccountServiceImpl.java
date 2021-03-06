package com.mn.trans.service;

import com.mn.trans.dao.TblAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private JdbcOperations devJdbcOperations;

    @Autowired
    private JdbcOperations prodJdbcOperations;

    @Autowired
    private TblAccountDao accountDao;


    @Transactional(rollbackFor = Exception.class)
    public void transfer(int outter, int inner, Integer money) {

        accountDao.moveOut(outter, money); //转出

        int i = 1/0;  // 抛出异常

        accountDao.moveIn(inner, money); //转入

    }



    @Transactional(value = "prodTransactionManager")
    public List<Map<String, Object>> prod() {
        return prodJdbcOperations.queryForList("SELECT * FROM user");
    }

    @Transactional(value = "devTransactionManager")
    public List<Map<String, Object>> dev() {
        return devJdbcOperations.queryForList("SELECT * FROM user2");
    }
}