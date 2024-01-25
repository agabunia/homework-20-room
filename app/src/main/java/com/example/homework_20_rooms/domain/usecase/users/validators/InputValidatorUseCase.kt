package com.example.homework_20_rooms.domain.usecase.users.validators

class InputValidatorUseCase {
    operator fun invoke(firstName: String, lastName: String, age: String, email: String): Boolean {
        return firstName.isNotBlank() && lastName.isNotBlank() && age.isNotBlank() && email.isNotBlank()
    }
}
