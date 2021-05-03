package com.mn.trans.dao;

import com.mn.trans.pojo.TblAccount;
import org.apache.ibatis.annotations.Param;

//@Mapper
public interface TblAccountDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TblAccount record);

    int insertSelective(TblAccount record);

    TblAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TblAccount record);

    int updateByPrimaryKey(TblAccount record);

    public void moveIn(@Param("id") int id, @Param("money") float money); // 转入

    public void moveOut(@Param("id") int id, @Param("money") float money); // 转出
}