package com.vimal.margh.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "model_list")
class ModelWallpaper(
    @field:PrimaryKey
    var id: Int,
    var previewURL: String,
    var webformatURL: String,
    var largeImageURL: String
) : Serializable 