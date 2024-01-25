package com.example.homework_20_rooms.presentation.mapper.users

import com.example.homework_20_rooms.domain.model.users.User
import com.example.homework_20_rooms.presentation.model.users.UserModel

fun User.toPresenter() : UserModel {
    return UserModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )
}

fun UserModel.toDomain() : User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        age = age,
        email = email
    )
}