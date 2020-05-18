package com.example.searchmovies.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmovies.domain.dto.Movie
import com.example.searchmovies.domain.usecase.SearchMovieUseCase
import com.example.searchmovies.view.MoviesViewEntity
import com.example.searchmovies.view.TheBestMovieViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel : ViewModel() {

    private val searchMoviesUseCase = SearchMovieUseCase()


    private val searchedMoviesLiveData = MutableLiveData<MoviesViewEntity>()

    fun getSearchedMovies(): LiveData<MoviesViewEntity> = searchedMoviesLiveData

    private val disposable = CompositeDisposable()

    fun onSearchClick(searchText: CharSequence) {
        searchMovie(searchText.toString())
    }

    private fun searchMovie(searchText: String) {
        disposable.add(
            searchMoviesUseCase.perform(searchText)
                .subscribeOn(Schedulers.io())
                .map { movies ->
                    val theBestMovie = findTheBestMovie(movies)
                    MoviesViewEntity(
                        movies.joinToString { "${it.title} - ${it.releaseDate} " },
                        TheBestMovieViewEntity(
                            theBestMovie.popularity,
                            theBestMovie.voteAverage,
                            theBestMovie.voteCount,
                            theBestMovie.adult,
                            theBestMovie.title,
                            theBestMovie.overview,
                            theBestMovie.releaseDate,
                            theBestMovie.posterPath
                        )
                    )
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { movieViewEntities ->
                    searchedMoviesLiveData.value = movieViewEntities
                }
        )
    }

    private fun findTheBestMovie(movies: List<Movie>): Movie {
        return movies.filter { !it.adult }
            .map { it to (it.popularity + (it.voteAverage * 10)) / 2 + it.voteAverage / 1000 }
            .maxBy { it.second }!!
            .first
    }
}
