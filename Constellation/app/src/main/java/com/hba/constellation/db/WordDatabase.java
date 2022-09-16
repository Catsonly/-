package com.hba.constellation.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.hba.constellation.db.bean.User;
import com.hba.constellation.db.dao.UserDao;

import org.w3c.dom.Comment;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    public abstract UserDao getUserDao(); // 若有多个 Entity，则应该写多个 Dao



}
