package com.example.breel.di

import com.example.breel.data.api.ApiConfig
import com.example.breel.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideHelloWorld(): String = "Hello World"

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiConfig.getApiService()

}