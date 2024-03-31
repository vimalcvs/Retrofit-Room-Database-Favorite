package com.vimal.margh.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vimal.margh.models.ModelWallpaper;


@Database(entities = {ModelWallpaper.class}, version = 1)
public abstract class FavoriteDatabase extends RoomDatabase {

    private static volatile FavoriteDatabase instance;

    public static synchronized FavoriteDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), FavoriteDatabase.class, "DATABASE").build();
        }
        return instance;
    }

    public abstract FavoriteDao setFavoriteDao();
}