package com.hba.constellation;

import android.app.Application;

import androidx.room.Room;

import com.hba.constellation.db.WordDatabase;


public class MyAPP extends Application {
    private static MyAPP instance;
    private WordDatabase wordDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public synchronized WordDatabase getWordDatabase() {
        if (wordDatabase == null) {
            wordDatabase = Room.databaseBuilder(this, WordDatabase.class, "word_database")
                    .fallbackToDestructiveMigration()//数据库更新时删除数据重新创建
                    .allowMainThreadQueries().build();

        }
        return wordDatabase;
    }

    public static MyAPP getInstance() {
        return instance;
    }
}
