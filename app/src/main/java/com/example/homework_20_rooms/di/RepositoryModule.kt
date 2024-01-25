package com.example.homework_20_rooms.di

import com.example.homework_20_rooms.data.local.dao.UserDao
import com.example.homework_20_rooms.data.repository.users.LocalUserRepositoryImp
import com.example.homework_20_rooms.domain.repository.users.LocalUserRepository
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
    fun provideLocalDatabaseRepository(
        userDao: UserDao
    ): LocalUserRepository {
        return LocalUserRepositoryImp(userDao = userDao)
    }
}
