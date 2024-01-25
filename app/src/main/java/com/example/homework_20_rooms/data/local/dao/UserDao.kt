package com.example.homework_20_rooms.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.homework_20_rooms.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM userentity")
    fun getAll(): Flow<List<UserEntity>>

    @Query("SELECT COUNT(*) FROM userentity")
    fun getUsersCount(): Flow<Int>

    @Query("SELECT * FROM userentity WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<UserEntity>>

    @Query(
        "SELECT * FROM userentity WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): Flow<UserEntity>

    @Query("SELECT * FROM userentity WHERE email LIKE :email LIMIT 1")
    fun findByEmail(email: String): Flow<UserEntity>

    @Query("SELECT * FROM userentity WHERE email LIKE :email AND first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findUser(first: String, last: String, email: String): Flow<UserEntity>

    @Insert
    suspend fun insert(vararg users: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)
}
