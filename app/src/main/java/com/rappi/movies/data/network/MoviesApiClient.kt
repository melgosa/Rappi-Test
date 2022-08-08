package com.rappi.movies.data.network

import com.rappi.movies.core.utils.APIConstants
import com.rappi.movies.data.model.movies.MoviesModel
import com.rappi.movies.data.model.trailer.TrailerResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApiClient {
    @GET(APIConstants.PATH_UPCOMING_MOVIES)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = APIConstants.API_KEY,
        @Query("language") language: String = APIConstants.LANGUAGE,
        @Query("page") page: String = "1"
    ): Response<MoviesModel>

    @GET(APIConstants.PATH_TOP_RATED_MOVIES)
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = APIConstants.API_KEY,
        @Query("language") language: String = APIConstants.LANGUAGE,
        @Query("page") page: String = "1"
    ): Response<MoviesModel>

    @GET(APIConstants.SEMI_PATH_MOVIE + "{movieId}" + APIConstants.SEMI_PATH_VIDEOS)
    suspend fun getTrailerResults(
        @Path(value = "movieId", encoded = false) movieId: String,
        @Query("api_key") apiKey: String = APIConstants.API_KEY
    ): Response<TrailerResults>
}