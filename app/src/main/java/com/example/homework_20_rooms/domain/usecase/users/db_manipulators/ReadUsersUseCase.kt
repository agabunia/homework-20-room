package com.example.homework_20_rooms.domain.usecase.users.db_manipulators

import com.example.homework_20_rooms.domain.repository.users.LocalUserRepository
import javax.inject.Inject

class ReadUsersUseCase @Inject constructor(private val repository: LocalUserRepository) {
    suspend operator fun invoke() = repository.getAll()
}