package com.vimal.margh.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vimal.margh.activity.ActivityDetail
import com.vimal.margh.adapter.AdapterFavorite
import com.vimal.margh.databinding.FragmentFavoriteBinding
import com.vimal.margh.db.Repository
import com.vimal.margh.models.ModelWallpaper
import com.vimal.margh.util.Constant.EXTRA_KEY

class FragmentFavorite : Fragment(), AdapterFavorite.OnItemClickListener {
    var repository: Repository? = null
    private var binding: FragmentFavoriteBinding? = null
    private var productAdapter: AdapterFavorite? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding!!.root

        repository = Repository.getInstance(requireActivity())

        initViews()
        return root
    }

    private fun initViews() {
        productAdapter = AdapterFavorite(requireActivity(), ArrayList())
        val productList = repository!!.products

        productList.observe(requireActivity()) { products: List<ModelWallpaper>? ->
            if (products != null) {
                productAdapter!!.changeData(products)
            }
        }


        binding!!.rvRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding!!.rvRecycler.adapter = productAdapter
        productAdapter!!.setOnItemClickListener(this)
    }

    override fun onItemClick(modelWallpaper: ModelWallpaper?) {
        val intent = Intent(requireActivity(), ActivityDetail::class.java)
        intent.putExtra(EXTRA_KEY, modelWallpaper)
        startActivity(intent)
    }

    override fun onItemDelete(modelWallpaper: ModelWallpaper?) {
        repository!!.deleteFavoriteProduct(modelWallpaper)
        Toast.makeText(requireActivity(), "Deleted to Favorite", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}