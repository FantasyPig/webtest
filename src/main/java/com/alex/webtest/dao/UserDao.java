package com.alex.webtest.dao;

import com.alex.webtest.utils.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {

    @Select("select * from user where id = #{id}")
    public User getById(@Param("id") long id);

    @Update("update user set password = #{password} where id = #{id}")
    public void upodate(User user);

}
