package com.example.knewz.domain.usecase

import com.example.knewz.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun execute(idToken: String): Result<Unit> =
        repository.signInWithGoogle(idToken)
}