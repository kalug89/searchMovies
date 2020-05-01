package com.example.searchmovies.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchmovies.domain.dto.Movie
import com.example.searchmovies.domain.usecase.SearchMovieUseCase
import com.example.searchmovies.view.MovieViewEntity
import com.example.searchmovies.view.MoviesContentViewEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel : ViewModel() {

    private val searchMoviesUseCase = SearchMovieUseCase()

    private val searchedMoviesLiveData = MutableLiveData<MoviesContentViewEntity>()

    fun getSearchedMovies():LiveData<MoviesContentViewEntity> = searchedMoviesLiveData

    private val disposable = CompositeDisposable()

    fun onSearchClick(searchText: CharSequence) {
        searchMovie(searchText.toString())
    }

    private fun searchMovie(searchText: String) {
        disposable.add(
        searchMoviesUseCase.perform(searchText)
            .subscribeOn(Schedulers.io())
            .map { movies -> movies.map { MovieViewEntity("${it.title} - ${it.releaseDate}") } }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { movieViewEntities -> searchedMoviesLiveData.value = MoviesContentViewEntity(movieViewEntities) }
        )
    }

}