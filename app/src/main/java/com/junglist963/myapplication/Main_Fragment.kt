package com.junglist963.myapplication


import android.annotation.SuppressLint
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.junglist963.myapplication.databinding.FragmentBinding

class Main_Fragment: Fragment() {

    var i : String? = null
    var x : Int? = null
    private var _binding : FragmentBinding? = null
    private val binding get() = _binding!!

    var listModel : List<Model> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBinding.inflate(inflater, container, false)
        binding.apply {
            viewPager.adapter = context?.let { ViewPagerAdapter(it, listModel) }
            viewPager.post{
                x?.let { viewPager.setCurrentItem(it, false) }
        }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


