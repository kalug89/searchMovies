package com.example.searchmovies.model

import com.example.searchmovies.domain.dto.Movie
import com.example.searchmovies.model.dto.ApiMovies

fun ApiMovies.toDomain(): Movie {
    return Movie(
        title,
        releaseDate
    )
}