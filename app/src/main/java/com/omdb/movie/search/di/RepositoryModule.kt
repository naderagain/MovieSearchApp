package com.omdb.movie.search.di

import com.omdb.movie.search.models.MoviesResponse
import com.omdb.movie.search.network.ApiHelper
import com.omdb.movie.search.repository.IMoviesRepository
import com.omdb.movie.search.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: ApiHelper
    ) : IMoviesRepository= MoviesRepository(remoteDataSource)

}