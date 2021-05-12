
一、数据库查询：外连接与不使用连接。

        注：不适用连接，只取有值的外键

1、不使用连接：
       
     #取交集（谨慎：只取有值的外键）。       
      
        select * from user2 u, address a where u.addr_id = a.id group by a.addr_code DEsc;

2、使用连接：
      
     #左连接：以左表为主，取左表键有值的数据行。
        
        select * from address a left join user2 u on u.addr_id = a.id group by a.addr_code DEsc;
        
二、数据库操作   

    1、表操作
    
            希望删除表结构时，用 drop;
            希望保留表结构，但要删除所有记录时， 用 truncate;
            希望保留表结构，但要删除部分记录时， 用 delete。
          
           
           1.drop table xx --xx是数据表的名字
           
           作用：删除内容和定义，释放空间。简单来说就是把整个表去掉.以后要新增数据是不可能的,除非新增一个表。
           
           把表的结构也删除了 下次要使用的时候要重新创建表的结构再插入数据
           
           2.truncate table xx
           
           作用：删除内容、释放空间但不删除定义。与drop不同的是,他只是清空表数据而已,不删除表结构。
           
           truncate 释放空间的体现。truncate table test 后 向test表添加数据,id标识列连续了(体现了truncate删除是释放空间）
           
           插入的字段的id重新从1开始递增 1、2、3.....
           
           3.delete table xx
           
           作用：也是删除整个表中的数据 表结构不会删除,但是过程是痛苦的(系统一行一行地删,效率较truncate低) 或delete table tb where 条件
           
           删除内容不删除定义，不释放空间。
           
           用delete删除 数据，然后添加。可以看到添加之后id标识不连续。（说明delete删除不释放空间）
    
        
    
                 
        