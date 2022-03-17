package com.unalzafer.moviescatalog.model.moviedetail

data class MovieDetailType(
    val id: Int,
    val overview: String,
    val popularity: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: String,
    val voteCount: String
)