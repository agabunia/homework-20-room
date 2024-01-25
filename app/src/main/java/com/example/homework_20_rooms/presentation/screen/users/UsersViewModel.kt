package com.example.homework_20_rooms.presentation.screen.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_20_rooms.domain.usecase.users.db_manipulators.DeleteUserUseCase
import com.example.homework_20_rooms.domain.usecase.users.db_manipulators.GetUsersCountUseCase
import com.example.homework_20_rooms.domain.usecase.users.db_manipulators.InsertUserUseCase
import com.example.homework_20_rooms.domain.usecase.users.db_manipulators.UpdateUserUseCase
import com.example.homework_20_rooms.domain.usecase.users.validators.InputValidatorUseCase
import com.example.homework_20_rooms.presentation.event.UsersEvent
import com.example.homework_20_rooms.presentation.mapper.users.toDomain
import com.example.homework_20_rooms.presentation.model.users.UserModel
import com.example.homework_20_rooms.presentation.state.users.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val inputValidatorUseCase: InputValidatorUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUsersCountUseCase: GetUsersCountUseCase
) : ViewModel() {

    private val _userState = MutableStateFlow(UserState())
    val userState: SharedFlow<UserState> = _userState.asStateFlow()

    fun onEvent(event: UsersEvent) {
        when (event) {
            is UsersEvent.CreateUser -> {
                addUser(
                    firstName = event.firstName,
                    lastName = event.lastName,
                    age = event.age,
                    email = event.email
                )
            }

            is UsersEvent.DeleteUser -> {
                deleteUser(
                    firstName = event.firstName,
                    lastName = event.lastName,
                    age = event.age,
                    email = event.email
                )
            }

            is UsersEvent.UpdateUser -> {
                updateUser(
                    firstName = event.firstName,
                    lastName = event.lastName,
                    age = event.age,
                    email = event.email
                )
            }

            is UsersEvent.ResetMessage -> {
                userMessage(message = null)
            }
        }
    }

    private fun addUser(firstName: String, lastName: String, age: String, email: String) {
        val isInputFilled = inputValidatorUseCase(
            firstName = firstName,
            lastName = lastName,
            age = age,
            email = email
        )

        if (!isInputFilled) {
            userMessage(message = "All fields must be filled!")
        } else {
            insert(firstName = firstName, lastName = lastName, age = age.toInt(), email = email)
            userMessage(message = "User was added Successfully!")
        }

    }

    private fun insert(firstName: String, lastName: String, age: Int, email: String) {
        val id = (0..1000).random()
        viewModelScope.launch {
            insertUserUseCase(
                UserModel(
                    id = id,
                    firstName = firstName,
                    lastName = lastName,
                    age = age,
                    email = email
                ).toDomain()
            )
        }
    }

    private fun deleteUser(firstName: String, lastName: String, age: String, email: String) {
        val isInputFilled = inputValidatorUseCase(
            firstName = firstName,
            lastName = lastName,
            age = age,
            email = email
        )

        if (!isInputFilled) {
            userMessage(message = "All fields must be filled!")
        } else {
            delete(first = firstName, last = lastName, email = email)
            userMessage(message = "User was deleted Successfully!")
        }
    }

    private fun delete(first: String, last: String, email: String) {
        viewModelScope.launch {
            deleteUserUseCase(first = first, last = last, email = email)
        }
    }

    private fun updateUser(firstName: String, lastName: String, age: String, email: String) {
        if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
            val userAge = age.toIntOrNull()
            if (userAge != null) {
                update(first = firstName, last = lastName, email = email, age = userAge)
                userMessage(message = "User was updated Successfully")
            } else {
                userMessage(message = "Invalid age format")
            }
        } else {
            userMessage(message = "All fields must be filled!")
        }
    }

    private fun update(first: String, last: String, email: String, age: Int) {
        viewModelScope.launch {
            updateUserUseCase(first = first, last = last, email = email, age = age)
        }
    }

    private fun userMessage(message: String?) {
        _userState.update { currentState -> currentState.copy(userMessage = message) }
    }

}
