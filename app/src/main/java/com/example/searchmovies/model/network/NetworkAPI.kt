package com.example.searchmovies.model.network

import com.example.searchmovies.model.dto.Results
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {

    @GET("/3/search/movie")
    fun searchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Single<Results>
}
