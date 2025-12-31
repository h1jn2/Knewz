package com.example.knewz.domain.repository

interface AuthRepository {
    suspend fun signInWithEmail(email: String, password: String): Result<Unit>
    suspend fun signInWithGoogle(idToken: String): Result<Unit>
    suspend fun signUpWithEmail(name: String, email: String, password: String): Result<Unit>
}