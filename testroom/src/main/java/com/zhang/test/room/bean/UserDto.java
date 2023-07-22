package com.zhang.test.room.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

/**
 * 用户对象
 *
 * @author ZhangXiaoMing 2023-07-22 20:37 周六
 */

@Entity(tableName = "CtmAccount")
public class UserDto {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int id;
    @ColumnInfo
    private String code;
    @ColumnInfo
    private String name;


    //<editor-fold desc="Getter and Setter">

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //</editor-fold>


    @NonNull
    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    public static UserDto generateUserInfo() {
        UserDto user = new UserDto();

        String uuid = UUID.randomUUID().toString();
        String[] stringArray = uuid.split("-");
        user.setCode(uuid);
        user.setName(stringArray[0]);

        return user;
    }

}
