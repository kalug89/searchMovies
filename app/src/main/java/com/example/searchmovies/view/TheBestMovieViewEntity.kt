package com.example.searchmovies.view

data class TheBestMovieViewEntity(
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val adult: Boolean,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String
)
