package com.example.knewz.domain.usecase

import com.example.knewz.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpWithEmailUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun execute(name: String, email: String, password: String): Result<Unit> =
        repository.signUpWithEmail(name, email, password)
}