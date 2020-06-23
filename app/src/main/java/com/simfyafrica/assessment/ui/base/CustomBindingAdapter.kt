package com.simfyafrica.assessment.ui.base

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.simfyafrica.assessment.R

object CustomBindingAdapter {

    @JvmStatic
    @BindingAdapter("app:image_url")
    fun loadImage(imageView: AppCompatImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(imageView)
    }
}