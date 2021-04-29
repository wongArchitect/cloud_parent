package com.mn.springboot_redis;

import com.mn.springboot_redis.dao.RedisDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootRedisApplication.class)
@Slf4j
public class SpringbootRedisApplicationTests {

    @Test
    public void contextLoads(){}

    @Resource
    private RedisDao redisDao;

    @Test
    public void testRedis() {
        redisDao.setKey("name","jerry");
        redisDao.setKey("age","11");
        log.info(redisDao.getValue("name"));
        log.info(redisDao.getValue("age"));
    }

}

