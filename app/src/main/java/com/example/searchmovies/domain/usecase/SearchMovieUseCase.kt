package com.example.searchmovies.domain.usecase

import com.example.searchmovies.domain.dto.Movie
import com.example.searchmovies.model.MovieRepository
import io.reactivex.Single

class SearchMovieUseCase {
    fun perform(searchText:String): Single<List<Movie>> {
        return  MovieRepository.searchMovie(searchText)

    }
}