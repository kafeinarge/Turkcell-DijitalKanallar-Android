package com.unalzafer.moviescatalog.ui.favorites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalzafer.moviescatalog.model.common.GenerateToken
import com.unalzafer.moviescatalog.model.search.MoviesResponse
import com.unalzafer.moviescatalog.repository.FavoritesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject
constructor(private val repository: FavoritesRepository,private val generateToken: GenerateToken) : ViewModel() {

    private val _response = MutableLiveData<MoviesResponse>()
    val responseMovies: LiveData<MoviesResponse> = _response

    fun getFavorites()=viewModelScope.launch{
        repository.getFavorites(generateToken.requestToken()).let{response->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.e("Error", "getWatchListMovies Error: ${response.code()}")
            }
        }
    }
}