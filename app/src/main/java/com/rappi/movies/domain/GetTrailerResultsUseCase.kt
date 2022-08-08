package com.rappi.movies.domain

import com.rappi.movies.data.model.trailer.TrailerResult
import com.rappi.movies.data.repository.MoviesRepository
import javax.inject.Inject

class GetTrailerResultsUseCase @Inject constructor(private val repository : MoviesRepository){

    suspend operator fun invoke(videoId: String):List<TrailerResult> = repository.getTrailerResultsFromApi(videoId)
}