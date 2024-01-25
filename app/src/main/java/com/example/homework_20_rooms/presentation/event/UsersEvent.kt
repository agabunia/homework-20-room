package com.example.homework_20_rooms.presentation.event

sealed class UsersEvent {
    data class CreateUser(val firstName: String, val lastName: String, val age: String, val email: String) : UsersEvent()
    data class DeleteUser(val firstName: String, val lastName: String, val age: String, val email: String) : UsersEvent()
    class UpdateUser(val firstName: String, val lastName: String, val age: String, val email: String) : UsersEvent()
    object ResetMessage: UsersEvent()
}
