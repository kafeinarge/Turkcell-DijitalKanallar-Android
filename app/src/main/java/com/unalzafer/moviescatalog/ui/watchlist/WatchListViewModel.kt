package com.unalzafer.moviescatalog.ui.watchlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalzafer.moviescatalog.model.common.GenerateToken
import com.unalzafer.moviescatalog.model.search.MoviesResponse
import com.unalzafer.moviescatalog.repository.WatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject
constructor(private val repository: WatchListRepository,private val generateToken: GenerateToken) : ViewModel() {

    private val _response = MutableLiveData<MoviesResponse>()
    val responseMovies: LiveData<MoviesResponse> = _response

    fun getWatchListMovies()=viewModelScope.launch{
        repository.getWatchList(generateToken.requestToken()).let{response->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.e("Error", "getWatchListMovies Error: ${response.code()}")
            }
        }
    }
}