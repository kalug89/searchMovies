package com.example.searchmovies.view

import android.os.Bundle
import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.searchmovies.R
import com.example.searchmovies.logic.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private val viewModel: MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        setupSearchButton()
        handleMovies()
    }

    private fun setupSearchButton() {
        view_search_button.setOnClickListener {
            viewModel.onSearchClick(view_search.text)
        }
    }

    private fun handleMovies() {
        viewModel.getSearchedMovies().observe(viewLifecycleOwner) { it ->
            view_content_video.text = it.moviesContent
            view_content_best_video.text =
                HtmlCompat.fromHtml(
                    "<b>${it.theBestMovie.title} </b> <br> " +
                            " ${it.theBestMovie.releaseDate} <br>" +
                            " ${it.theBestMovie.voteAverage} <br> <br>" +
                            " ${it.theBestMovie.overview}"
                    , HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            getSearchPoster(it.theBestMovie.posterPath)
        }
    }

    private fun getSearchPoster(moviePoster: String) {
        Glide.with(this).load("https://image.tmdb.org/t/p/original/${moviePoster}")
            .into(view_poster_image)
    }
}
