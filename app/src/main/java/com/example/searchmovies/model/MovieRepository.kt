package com.example.searchmovies.model

import com.example.searchmovies.domain.dto.Movie
import com.example.searchmovies.model.network.NetworkAPI
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "133b5b90caa75e7dcb3455ff73b989c0"
private const val BASE_URL = "https://api.themoviedb.org/"

object MovieRepository {

    private val networkApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(NetworkAPI::class.java)

    fun searchMovie(searchText: String): Single<List<Movie>> {
        return networkApi.searchMovies(API_KEY, searchText )
            .flatMapObservable { Observable.fromIterable(it.results) }
            .map { it.toDomain() }
            .toList()
    }
}
