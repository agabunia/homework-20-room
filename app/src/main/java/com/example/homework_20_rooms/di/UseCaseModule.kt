package com.example.homework_20_rooms.di

import com.example.homework_20_rooms.domain.usecase.users.validators.InputValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideInputValidatorUseCase(): InputValidatorUseCase{
        return InputValidatorUseCase()
    }
}