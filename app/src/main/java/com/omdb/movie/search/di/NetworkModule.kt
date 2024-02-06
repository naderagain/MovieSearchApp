package com.omdb.movie.search.di

import android.util.Log
import com.google.android.material.tabs.TabLayout.TabGravity
import com.google.gson.GsonBuilder
import com.omdb.movie.search.network.ApiHelper
import com.omdb.movie.search.network.ApiHelperImpl
import com.omdb.movie.search.network.MoviesApi
import com.omdb.movie.search.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val TAG = "NetworkModule"

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(TAG, message)
        }
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit):MoviesApi = retrofit.create(MoviesApi::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl):ApiHelper = apiHelperImpl
}