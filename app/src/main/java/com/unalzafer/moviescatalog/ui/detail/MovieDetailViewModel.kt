package com.unalzafer.moviescatalog.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalzafer.moviescatalog.model.common.BaseModelResponse
import com.unalzafer.moviescatalog.model.common.GenerateToken
import com.unalzafer.moviescatalog.model.moviedetail.MovieDetailResponse
import com.unalzafer.moviescatalog.model.moviedetail.MovieDetailType
import com.unalzafer.moviescatalog.model.search.MoviesResponse
import com.unalzafer.moviescatalog.repository.FavoritesRepository
import com.unalzafer.moviescatalog.repository.MovieDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
@Inject
constructor(
    private val repositoryMovie: MovieDetailRepository,
    private val favoritesRepository: FavoritesRepository,
    private val generateToken: GenerateToken
) : ViewModel() {

    var movieDetailType:MovieDetailType? = null
    var isFavorite:Boolean=false
    private val _response = MutableLiveData<MovieDetailResponse>()
    private val _responseBase = MutableLiveData<BaseModelResponse>()
    val responseMovies: LiveData<MovieDetailResponse> = _response
    val responseRemoveAndFavorite: LiveData<BaseModelResponse> = _responseBase
    private val _responseFavorite = MutableLiveData<MoviesResponse>()
    val responseFavorites: LiveData<MoviesResponse> = _responseFavorite

    fun getFavorites()=viewModelScope.launch{
        favoritesRepository.getFavorites(generateToken.requestToken()).let{response->
            if (response.isSuccessful){
                _responseFavorite.postValue(response.body())
            }else{
                Log.e("Error", "getWatchListMovies Error: ${response.code()}")
            }
        }
    }

    fun getMovieDetail(id: String) = viewModelScope.launch {
        repositoryMovie.getMovieDetail(id).let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.e("Error", "getMovieDetail Error: ${response.code()}")
            }
        }
    }

    fun addRemoveAndFavorite() = viewModelScope.launch {
        repositoryMovie.addRemoveAndFavorite(isFavorite, movieDetailType?.id.toString(), generateToken.requestToken())
            .let { response ->
                if (response.isSuccessful) {
                    _responseBase.postValue(response.body())
                } else {
                    Log.e("Error", "addRemoveAndFavorite Error: ${response.code()}")
                }
            }
    }
}