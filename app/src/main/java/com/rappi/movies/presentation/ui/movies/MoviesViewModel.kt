package com.rappi.movies.presentation.ui.movies

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rappi.movies.data.repository.MoviesRepository
import com.rappi.movies.domain.GetRecommendedMoviesUseCase
import com.rappi.movies.domain.GetTopRatedMoviesUseCase
import com.rappi.movies.domain.GetUpcomingMoviesUseCase
import com.rappi.movies.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getRecommendedMoviesUseCase: GetRecommendedMoviesUseCase
) : ViewModel() {
    val upcomingMoviesModel = MutableLiveData<List<Movie>>()
    val topRatedMoviesModel = MutableLiveData<List<Movie>>()
    val recommendedMoviesModel = MutableLiveData<List<Movie>>()

    fun onCreate() {
        viewModelScope.launch {
            val asyncUpcomingMovies = async { getUpcomingMoviesUseCase() }
            val asynTopRatedMovies = async { getTopRatedMoviesUseCase() }

            val (
                upcomingMovies,
                topRatedMovies
            ) = awaitAll(asyncUpcomingMovies, asynTopRatedMovies)

            upcomingMoviesModel.postValue(upcomingMovies)

            topRatedMoviesModel.postValue(topRatedMovies)

        }
    }

    fun getRecommendedMovies(language: String, launchYear: String) {
        viewModelScope.launch {
            val movies = getRecommendedMoviesUseCase(language, launchYear)
            if(movies.isNotEmpty()){
                recommendedMoviesModel.postValue(movies)
            }
        }
    }
}