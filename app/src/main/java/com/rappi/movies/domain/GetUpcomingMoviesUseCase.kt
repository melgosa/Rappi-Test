package com.rappi.movies.domain

import android.content.Context
import com.rappi.movies.core.utils.Utils
import com.rappi.movies.data.database.entities.toDatabase
import com.rappi.movies.data.repository.MoviesRepository
import com.rappi.movies.domain.model.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(private val repository: MoviesRepository, @ApplicationContext val context: Context){

    suspend operator fun invoke(): List<Movie>{

        return if(Utils.isConnected(context)){
            val movies = repository.getUpcomingMoviesFromApi()
            repository.clearUpcomingMovies()
            repository.insertMovies(movies.map { it.toDatabase() })
            movies
        }else{
            repository.getUpcomingMoviesFromDatabase()
        }
    }
}