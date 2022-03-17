package com.unalzafer.moviescatalog.api

import com.unalzafer.moviescatalog.helper.ConstantsService
import com.unalzafer.moviescatalog.model.common.BaseModelResponse
import com.unalzafer.moviescatalog.model.login.LoginUserRequest
import com.unalzafer.moviescatalog.model.login.LoginUserResponse
import com.unalzafer.moviescatalog.model.moviedetail.MovieDetailResponse
import com.unalzafer.moviescatalog.model.search.MoviesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET(ConstantsService.SEARCH)
    suspend fun getSearch(@Query("query") query: String): Response<MoviesResponse>

    @GET(ConstantsService.DETAIL_MOVIE)
    suspend fun getMovieDetail(@Query("movie_id") query: String): Response<MovieDetailResponse>

    @GET(ConstantsService.ACCOUNT_FAVORITE_MOVIES)
    suspend fun getAccountFavoriteMovies(
        @Query("account_id") accountId: String,
        @Query("session_id") sessionId: String
    ): Response<MoviesResponse>

    @GET(ConstantsService.ACCOUNT_WATCH_LIST_MOVIES)
    suspend fun getAccountWatchListMovies(
        @Query("account_id") accountId: String,
        @Query("session_id") sessionId: String
    ): Response<MoviesResponse>

    @POST(ConstantsService.LOGIN)
    suspend fun login(@Body loginUserRequest: LoginUserRequest): Response<LoginUserResponse>


    @POST(ConstantsService.ADD_REMOVE_FAVORITE)
    suspend fun addRemoveAndFavorite(
        @Query("favorite") favorite: Boolean,
        @Query("mediaId") mediaId: String,
        @Query("account_id") accountId: String,
        @Query("session_id") sessionId: String
    ): Response<BaseModelResponse>
}
