package com.vimal.margh.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vimal.margh.models.ModelWallpaper;


@Dao
interface FavoriteDao {

    @Query("Select * from model_list")
    fun getAllProducts(): LiveData<List<ModelWallpaper>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductToFavorite(modelWallpaper: ModelWallpaper)

    @Delete
    fun deleteProductFromFavorite(modelWallpaper: ModelWallpaper)

    @Query("SELECT EXISTS (SELECT 1 FROM model_list WHERE id = :id)")
    fun isFavorite(id: Int): Boolean
}