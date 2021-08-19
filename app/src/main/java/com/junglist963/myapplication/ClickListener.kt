package com.junglist963.myapplication

import android.os.Bundle

interface ClickListener {
    fun onItemClicked(model: Model, bundle: Bundle)
}