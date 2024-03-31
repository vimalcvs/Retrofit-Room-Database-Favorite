package com.vimal.margh.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.vimal.margh.R
import com.vimal.margh.databinding.ItemListBinding
import com.vimal.margh.db.Repository
import com.vimal.margh.models.ModelWallpaper
import com.vimal.margh.util.Utils.downloadImage
import com.vimal.margh.util.Utils.shareImage

class AdapterFavorite(val context: Context, private var list: List<ModelWallpaper>) :
    RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {
    private var mOnItemClickListener: OnItemClickListener? = null
    private val repository: Repository = Repository.getInstance(context)!!


    fun setOnItemClickListener(mItemClickListener: OnItemClickListener?) {
        this.mOnItemClickListener = mItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]




        val isFavorite = repository.isFavorite(model.id)
        if (isFavorite) {
            holder.binding.ivFavorite.setImageResource(R.drawable.ic_btm_nav_favorite_active)
        } else {
            holder.binding.ivFavorite.setImageResource(R.drawable.ic_btm_nav_favorite_normal)
        }

        holder.binding.ivSave.setOnClickListener {
            if (mOnItemClickListener != null) {
                mOnItemClickListener!!.onItemDelete(model)
            }
        }

        holder.binding.cvCard.setOnClickListener {
            if (mOnItemClickListener != null) {
                mOnItemClickListener!!.onItemClick(model)
            }
        }
    }


    fun changeData(productList: List<ModelWallpaper>) {
        this.list = productList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(modelWallpaper: ModelWallpaper?)

        fun onItemDelete(modelWallpaper: ModelWallpaper?)
    }


    class ViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(
        binding.root
    )
}