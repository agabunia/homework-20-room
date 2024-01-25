package com.example.homework_20_rooms.domain.usecase.users.db_manipulators

import com.example.homework_20_rooms.domain.model.users.User
import com.example.homework_20_rooms.domain.repository.users.LocalUserRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(private val repository: LocalUserRepository) {
    suspend operator fun invoke(first: String, last: String, email: String) {
        val user = repository.findUser(first = first, last = last, email = email).firstOrNull()
        user?.let {
            repository.delete(it)
        }
    }
}