package com.zhang.test.room.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.Gson;

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

    /** 性别：0-未知；1.男；2.女 */
    @ColumnInfo(defaultValue = "0", typeAffinity = ColumnInfo.INTEGER)
    private Integer gender = 0;

    @ColumnInfo
    private String country;
    @ColumnInfo
    private int age;


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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    //</editor-fold>


    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
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
