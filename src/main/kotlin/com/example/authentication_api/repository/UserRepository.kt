package com.example.authentication_api.repository

import com.example.authentication_api.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByPhoneNumber(phoneNumber: String): User?
    fun findByFirstNameAndLastName(firstName: String, lastName: String): List<User>
}