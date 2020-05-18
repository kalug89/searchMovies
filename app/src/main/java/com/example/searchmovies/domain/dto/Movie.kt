package com.example.searchmovies.domain.dto

data class Movie(
    val title: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val voteCount: Int,
    val adult: Boolean,
    val overview: String,
    val posterPath: String
)
