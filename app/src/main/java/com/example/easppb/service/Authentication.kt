package com.example.easppb.service

class Authentication {
    companion object {
        private val users = mutableMapOf<String, String>()
        fun authenticate(email: String, password: String): Boolean {
            return users[email] == password
        }
        fun registerUser(email: String, password: String) {
            users[email] = password
        }
    }
}