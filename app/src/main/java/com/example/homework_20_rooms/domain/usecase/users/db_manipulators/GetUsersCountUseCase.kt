package com.example.homework_20_rooms.domain.usecase.users.db_manipulators

import com.example.homework_20_rooms.domain.repository.users.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class GetUsersCountUseCase @Inject constructor(private val repository: LocalUserRepository) {
    suspend operator fun invoke(): Flow<Int> = repository.getUsersCount()
}
