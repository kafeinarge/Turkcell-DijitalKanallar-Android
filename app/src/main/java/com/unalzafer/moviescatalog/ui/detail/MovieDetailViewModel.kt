package com.unalzafer.moviescatalog.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalzafer.moviescatalog.model.common.BaseModelResponse
import com.unalzafer.moviescatalog.model.common.GenerateToken
import com.unalzafer.moviescatalog.model.moviedetail.MovieDetailResponse
import com.unalzafer.moviescatalog.model.search.MoviesResponse
import com.unalzafer.moviescatalog.repository.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
@Inject
constructor(
    private val repositoryMovie: MovieDetailRepository,
    private val generateToken: GenerateToken
) : ViewModel() {

    private val _response = MutableLiveData<MovieDetailResponse>()
    private val _responseBase = MutableLiveData<BaseModelResponse>()
    val responseMovies: LiveData<MovieDetailResponse> = _response
    val responseRemoveAndFavorite: LiveData<BaseModelResponse> = _responseBase

    fun getMovieDetail(id: String) = viewModelScope.launch {
        repositoryMovie.getMovieDetail(id).let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.e("Error", "getMovieDetail Error: ${response.code()}")
            }
        }
    }

    fun addRemoveAndFavorite(favorite: Boolean, mediaId: String) = viewModelScope.launch {
        repositoryMovie.addRemoveAndFavorite(favorite, mediaId, generateToken.requestToken())
            .let { response ->
                if (response.isSuccessful) {
                    _responseBase.postValue(response.body())
                } else {
                    Log.e("Error", "addRemoveAndFavorite Error: ${response.code()}")
                }
            }
    }
}