package com.unalzafer.moviescatalog.ui.detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.unalzafer.moviescatalog.R
import com.unalzafer.moviescatalog.base.BaseActivity
import com.unalzafer.moviescatalog.databinding.ActivityMovieDetailBinding
import com.unalzafer.moviescatalog.helper.ConstantsService
import com.unalzafer.moviescatalog.helper.enum.ResponseEnum
import com.unalzafer.moviescatalog.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {
    private val viewModel: MovieDetailViewModel by viewModels()
    var binding: ActivityMovieDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_movie_detail
        )
        setContentView(binding!!.root)

        if (!intent.getStringExtra(MainActivity::class.simpleName).isNullOrEmpty()) {
            viewModel.getMovieDetail(
                intent.getStringExtra(MainActivity::class.simpleName).toString()
            )
        } else {
            finish()
        }

        viewModel.responseMovies.observe(this) {
            if (it.responseCode == ResponseEnum.MOVIES_SUCCESS.value) {
                viewModel.movieDetailType = it.movieDetailType
                binding!!.movieDetail = it.movieDetailType
                Glide
                    .with(this)
                    .load(ConstantsService.POSTER_PATH_BASE_URL + ConstantsService.POSTER_PATH_SIZE_500 + it.movieDetailType.posterPath)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .placeholder(R.drawable.ic_movie_player_clapperboard)
                    .into(binding!!.ivPoster)
                favorites()
            } else {
                showDialog(it.responseDesc)
            }
        }

        binding!!.ivFavorite.setOnClickListener {
            viewModel.addRemoveAndFavorite()
            viewModel.responseRemoveAndFavorite.observe(this) {
                viewModel.isFavorite = !viewModel.isFavorite
                favoriteVisible()
            }
        }

    }

    fun favorites() {
        viewModel.getFavorites()
        viewModel.responseFavorites.observe(this) {
            if (it.responseCode == ResponseEnum.MOVIES_SUCCESS.value) {
                if (it.movieList.isNotEmpty()) {
                    for (item in it.movieList) {
                        if (item.id == viewModel.movieDetailType?.id) {
                            viewModel.isFavorite = true
                        }
                    }
                } else {
                    viewModel.isFavorite = false
                }
                favoriteVisible()
            } else {
                showDialog(it.responseDesc)
            }
        }
    }

    private fun favoriteVisible() {
        if (viewModel.isFavorite)
            binding!!.ivFavorite.setImageResource(R.drawable.ic_favorite)
        else
            binding!!.ivFavorite.setImageResource(R.drawable.ic_un_favorite)
    }
}