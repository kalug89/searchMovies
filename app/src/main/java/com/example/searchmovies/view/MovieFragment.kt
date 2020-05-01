package com.example.searchmovies.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.searchmovies.R
import com.example.searchmovies.logic.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import androidx.lifecycle.observe

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
            val movieContent = it.movies
                .joinToString { it.movieContent }
            view_content_video.text = movieContent
        }
    }

}