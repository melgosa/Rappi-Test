package com.rappi.movies.domain

import com.rappi.movies.data.model.movies.MoviesModel
import com.rappi.movies.data.repository.MoviesRepository
import com.rappi.movies.domain.model.Movie
import javax.inject.Inject

class GetRecommendedMoviesUseCase @Inject constructor(private val repository : MoviesRepository){
    suspend operator fun invoke(language:String, launchYear:String): List<Movie>{
        return repository.getRecommendedForYouMoviesFromDataBase(language,launchYear)
    }
}