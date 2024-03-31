package com.vimal.margh.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.vimal.margh.models.ModelWallpaper
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors

class Repository(context: Context?) {
    private val productDao: FavoriteDao
    val products: LiveData<List<ModelWallpaper>>

    init {
        val database = FavoriteDatabase.getDatabase(context!!)
        productDao = database.setFavoriteDao()
        products = productDao.getAllProducts()
    }

    fun deleteFavoriteProduct(product: ModelWallpaper?) {
        object : Thread(Runnable { productDao.deleteProductFromFavorite(product!!) }) {
        }.start()
    }

    fun insertProductToFavorite(product: ModelWallpaper?) {
        object : Thread(Runnable { productDao.insertProductToFavorite(product!!) }) {
        }.start()
    }

    fun isFavorite(id: Int): Boolean {
        val callable = Callable { productDao.isFavorite(id) }
        val future = Executors.newSingleThreadExecutor().submit(callable)
        try {
            return future.get()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
        return false
    }

    companion object {
        private var repository: Repository? = null
        fun getInstance(context: Context?): Repository? {
            if (repository == null) {
                repository = Repository(context)
            }
            return repository
        }
    }
}