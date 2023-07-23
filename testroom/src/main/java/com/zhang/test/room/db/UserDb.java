package com.zhang.test.room.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.zhang.test.room.bean.UserDto;
import com.zhang.test.room.dao.UserDbDao;

/**
 * @author ZhangXiaoMing 2023-07-22 20:42 周六
 */

@Database(entities = UserDto.class, version = 4, exportSchema = false)
public abstract class UserDb extends RoomDatabase {

    public abstract UserDbDao getUserDao();
}
