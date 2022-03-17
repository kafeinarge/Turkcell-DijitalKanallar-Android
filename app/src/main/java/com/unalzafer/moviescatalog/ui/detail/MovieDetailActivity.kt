package com.unalzafer.moviescatalog.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.unalzafer.moviescatalog.R
import com.unalzafer.moviescatalog.base.BaseActivity
import com.unalzafer.moviescatalog.databinding.ActivityMovieDetailBinding
import com.unalzafer.moviescatalog.helper.enum.ResponseEnum
import com.unalzafer.moviescatalog.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity() {
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMovieDetailBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_movie_detail
        )
        setContentView(binding.root)

        if (!intent.getStringExtra(MainActivity::class.simpleName).isNullOrEmpty()) {
            viewModel.getMovieDetail(
                intent.getStringExtra(MainActivity::class.simpleName).toString()
            )
        } else {
            finish()
        }
        viewModel.responseMovies.observe(this) {
            if (it.responseCode == ResponseEnum.MOVIES_SUCCESS.value) {
                binding.movieDetail=it.movieDetailType
            } else {
                showDialog(it.responseDesc)
            }
        }

    }
}