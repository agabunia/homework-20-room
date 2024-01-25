package com.example.homework_20_rooms.domain.usecase.users.db_manipulators

import com.example.homework_20_rooms.domain.repository.users.LocalUserRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: LocalUserRepository) {
    suspend operator fun invoke(first: String, last: String, email: String, age: Int) {

        val user = repository.findByEmail(email = email).firstOrNull() ?: repository.findByName(
            first = first,
            last = last
        ).firstOrNull()

        user?.let {
            val updatedUser = it.copy(
                firstName = first,
                lastName = last,
                email = email,
                age = age.takeIf { it > 0 }
            )
            repository.update(updatedUser)
        }
    }
}