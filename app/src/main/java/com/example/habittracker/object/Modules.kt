package com.example.habittracker.`object`

import com.example.habittracker.repository.FirestoreRepository.FirestoreRepository
import com.example.habittracker.repository.FirestoreRepository.FirestoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFirestoreRepository(): FirestoreRepository {
        return FirestoreRepositoryImpl()
    }
}