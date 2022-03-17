package com.unalzafer.moviescatalog.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unalzafer.moviescatalog.model.search.MoviesResponse
import com.unalzafer.moviescatalog.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
@Inject
constructor(private val repository: MoviesRepository) : ViewModel() {

    private val _response = MutableLiveData<MoviesResponse>()
    val responseMovies: LiveData<MoviesResponse> = _response

    fun getAllMovies(text:String)=viewModelScope.launch{
        repository.getSearch(text).let{response->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.e("Error", "getAllMovies Error: ${response.code()}")
            }
        }
    }
}