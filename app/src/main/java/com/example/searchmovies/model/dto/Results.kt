package com.example.searchmovies.model.dto

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("results")
    val results: List<ApiMovies>
)
