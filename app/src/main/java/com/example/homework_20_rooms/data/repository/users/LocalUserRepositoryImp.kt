package com.example.homework_20_rooms.data.repository.users

import android.util.Log.d
import com.example.homework_20_rooms.data.local.dao.UserDao
import com.example.homework_20_rooms.data.local.entity.UserEntity
import com.example.homework_20_rooms.data.local.mapper.toData
import com.example.homework_20_rooms.data.local.mapper.toDomain
import com.example.homework_20_rooms.domain.model.users.User
import com.example.homework_20_rooms.domain.repository.users.LocalUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalUserRepositoryImp @Inject constructor(
    private val userDao: UserDao
) : LocalUserRepository {

    override fun getAll(): Flow<List<User>> {
        return userDao.getAll().map { users ->
            users.map {
                it.toDomain()
            }
        }
    }

    override fun getUsersCount(): Flow<Int> {
        return userDao.getUsersCount()
    }

    override fun loadAllByIds(userIds: IntArray): Flow<List<User>> {
        return userDao.loadAllByIds(userIds).map { users ->
            users.map {
                it.toDomain()
            }
        }
    }

    override fun findByName(first: String, last: String): Flow<User> {
        return userDao.findByName(first = first, last = last).map { users ->
            users.toDomain()
        }
    }

    override fun findByEmail(email: String): Flow<User> {
        return userDao.findByEmail(email = email).map { users ->
            users.toDomain()
        }
    }

    override fun findUser(first: String, last: String, email: String): Flow<User> {
        return userDao.findUser(first = first, last = last, email = email).map {
            it.toDomain()
        }
    }

    override suspend fun insert(users: User) {
        userDao.insert(users.toData())
    }

    override suspend fun update(user: User) {
        userDao.update(user.toData())
    }

    override suspend fun delete(user: User) {
        userDao.delete(user.toData())
    }

}
