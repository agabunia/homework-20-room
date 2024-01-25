package com.example.homework_20_rooms.data.local.mapper

import com.example.homework_20_rooms.data.local.entity.UserEntity
import com.example.homework_20_rooms.domain.model.users.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )
}

fun User.toData(): UserEntity {
    return UserEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )
}
