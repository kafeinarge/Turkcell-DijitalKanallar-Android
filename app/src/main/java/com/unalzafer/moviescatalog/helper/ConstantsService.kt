package com.unalzafer.moviescatalog.helper

object ConstantsService {

    const val BASE_URL="http://46.101.105.123:8080"

    const val  POSTER_PATH_BASE_URL="https://image.tmdb.org/t/p/"
    const val  POSTER_PATH_SIZE_200="w200"
    private const val  MOVIE="/movie"
    private const val  ACCOUNT="/account"
    private const val  FAVORITE="/favorite"
    private const val  WATCH_LIST="/watch_list"

    const val  SEARCH= "$MOVIE/search"
    const val  LOGIN="user/login"
    const val  ACCOUNT_FAVORITE_MOVIES= "$ACCOUNT$FAVORITE/movies"
    const val  ACCOUNT_WATCH_LIST_MOVIES= "$ACCOUNT$WATCH_LIST/movies"
    const val  DETAIL_MOVIE= "$MOVIE/detail"
    const val  ADD_REMOVE_FAVORITE= "$ACCOUNT$FAVORITE/add_remove"

}