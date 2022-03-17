package com.unalzafer.moviescatalog.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.unalzafer.moviescatalog.R
import com.unalzafer.moviescatalog.helper.ConstantsService

@BindingAdapter("imageUrl")
fun loadImageSmall(imageView: ImageView, imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        try {
            Glide
                .with(imageView.context)
                .load(ConstantsService.POSTER_PATH_BASE_URL + ConstantsService.POSTER_PATH_SIZE_200 + imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_movie_player_clapperboard)
                .into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}