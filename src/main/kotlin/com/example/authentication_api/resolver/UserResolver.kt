package com.example.authentication_api.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.authentication_api.model.User
import com.example.authentication_api.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserResolver(private val userRepository: UserRepository) : GraphQLQueryResolver {

    fun getUserById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    fun getUserByPhoneNumber(phoneNumber: String): User? {
        return userRepository.findByPhoneNumber(phoneNumber)
    }

    fun getUserByName(firstName: String, lastName: String): List<User> {
        return userRepository.findByFirstNameAndLastName(firstName, lastName)
    }


}