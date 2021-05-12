
数据库查询：外连接与不使用连接。

        注：不适用连接，只取有值的外键

1、不使用连接：
       
     #取交集（谨慎：只取有值的外键）。       
      
        select * from user2 u, address a where u.addr_id = a.id group by a.addr_code DEsc;

2、使用连接：
      
     #左连接：以左表为主，取左表键有值的数据行。
        
        select * from address a left join user2 u on u.addr_id = a.id group by a.addr_code DEsc;