package com.example.authentication_api.service

import com.example.authentication_api.model.User
import com.example.authentication_api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val userRepository: UserRepository) {

    fun authenticatePhoneNumber(phoneNumber: String): UserResponse {
        val user = userRepository.findByPhoneNumber(phoneNumber)

        return if (user != null) {
            // Пользователь существует - запросите пароль от пользователя
            UserResponse(EXISTING_USER, null)
        } else {
            // Пользователя нет - запросите имя и фамилию для создания нового пользователя
            UserResponse(NEW_USER, null)
        }
    }

    fun authenticatePassword(phoneNumber: String, password: String): UserResponse {
        val user = userRepository.findByPhoneNumber(phoneNumber)

        return if (user != null && user.password == password) {

            UserResponse(SUCCESS, user)
        } else {

            UserResponse(INVALID_PASSWORD, null)
        }
    }

    fun authenticateSmsCode(phoneNumber: String, smsCode: String): UserResponse {
        val user = userRepository.findByPhoneNumber(phoneNumber)

        return if (smsCode == "111111") {

            UserResponse(SUCCESS, user)
        } else {

            UserResponse(INVALID_SMS_CODE, null)
        }
    }

    companion object {
        const val SUCCESS = "SUCCESS"
        const val EXISTING_USER = "EXISTING_USER"
        const val NEW_USER = "NEW_USER"
        const val INVALID_PASSWORD = "INVALID_PASSWORD"
        const val INVALID_SMS_CODE = "INVALID_SMS_CODE"
    }
}

data class UserResponse(val message: String, val user: User?) {
}