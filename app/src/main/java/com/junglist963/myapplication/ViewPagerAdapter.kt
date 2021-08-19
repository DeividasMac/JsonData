package com.junglist963.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.junglist963.myapplication.databinding.ItemViewpager2Binding

class ViewPagerAdapter(private val contextM: Context ,private val imgList: List<Model>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolderViewPager>() {

    class ViewHolderViewPager(val binder: ItemViewpager2Binding): RecyclerView.ViewHolder(binder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderViewPager {
        return ViewHolderViewPager(ItemViewpager2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderViewPager, position: Int) {
        val curImage = imgList[position]
        holder.binder.apply {
            Glide.with(contextM).asBitmap().load(curImage.urls).into(imagePager2)
        }
    }

    override fun getItemCount(): Int {
       return imgList.size
    }
}