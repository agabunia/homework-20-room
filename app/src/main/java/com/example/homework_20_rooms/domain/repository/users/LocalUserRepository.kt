package com.example.homework_20_rooms.domain.repository.users

import com.example.homework_20_rooms.domain.model.users.User
import kotlinx.coroutines.flow.Flow

interface LocalUserRepository {

    fun getAll(): Flow<List<User>>

    fun getUsersCount(): Flow<Int>

    fun loadAllByIds(userIds: IntArray): Flow<List<User>>

    fun findByName(first: String, last: String): Flow<User>

    fun findByEmail(email: String): Flow<User>

    fun findUser(first: String, last: String, email: String): Flow<User>

    suspend fun insert(users: User)

    suspend fun update(user: User)

    suspend fun delete(user: User)
}
