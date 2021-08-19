package com.junglist963.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.junglist963.myapplication.databinding.ActivityMainBinding.inflate
import com.junglist963.myapplication.databinding.CardItemBinding


class Adapter(
    private val context: Context,
    private val list: MutableList<Model>,
    private val clickListener: ClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val curList = list[position]
        holder.binding.apply {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(context).asBitmap().load(curList.urls).placeholder(circularProgressDrawable).into(imgDog)

            cardDog.setOnClickListener(View.OnClickListener {
                val bundle= Bundle()
                val i :String = curList.urls
                bundle.putString("img", i)
                bundle.putInt("position", position)
                clickListener.onItemClicked(curList, bundle)
            })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

     class ViewHolder (val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root)

}
