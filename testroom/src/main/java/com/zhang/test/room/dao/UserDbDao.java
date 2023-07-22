package com.zhang.test.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.zhang.test.room.bean.UserDto;

import java.util.List;

/**
 * @author ZhangXiaoMing 2023-07-22 20:41 周六
 */

@Dao
public interface UserDbDao {

    /** 获取所有的用户列表 */
    @Query("select * from CtmAccount")
    List<UserDto> getAllUser();

    @Query("select * from CtmAccount where id=:id limit 1")
    UserDto getUserById(String id);

    /**
     * 通过code查询用户信息
     *
     * @param code 指定的用户code
     */
    @Query("select * from CtmAccount where code like '%' || :code || '%' limit 1")
    UserDto getUserByAccountCode(String code);

    /**
     * 插入用户信息
     *
     * @param users 用户数组
     */
    @Insert
    void insertUserInfo(UserDto... users);

    /**
     * 删除用户数据
     *
     * @param user 用户
     */
    @Delete
    int deleteUserInfo(UserDto user);

}
