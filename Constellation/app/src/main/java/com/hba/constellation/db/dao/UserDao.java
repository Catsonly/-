package com.hba.constellation.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.hba.constellation.db.bean.User;


@Dao
public interface UserDao {
    @Insert
    void insert(User user);
    @Update
    void update(User user);

    @Query("select * from user where userName=:phone and userPassword= :pwd")
    User login(String phone, String pwd);

    @Query("select * from user where userName=:phone")
    User isExit(String phone);
}
