package com.example.authentication_api.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.authentication_api.model.User
import com.example.authentication_api.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserMutationResolver(private val userRepository: UserRepository) : GraphQLMutationResolver {
    fun createUser(phoneNumber: String, password: String,
                   firstName: String, lastName: String): User {
        val user = User(phoneNumber = phoneNumber,
            password = password,
            firstName = firstName,
            lastName = lastName)
        return userRepository.save(user)
    }

    fun updateUser(id: Long, phoneNumber: String?, password: String?,
                   firstName: String?, lastName: String?): User? {
        val user = userRepository.findById(id).orElse(null)

        if (user != null) {
            phoneNumber?.let { user.phoneNumber = it }
            password?.let { user.password = it }
            firstName?.let { user.firstName = it }
            lastName?.let { user.lastName = it }
            return userRepository.save(user)
        }

        return null
    }
}