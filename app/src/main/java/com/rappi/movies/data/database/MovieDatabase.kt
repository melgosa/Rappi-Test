package com.rappi.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rappi.movies.data.database.dao.MovieDao
import com.rappi.movies.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
