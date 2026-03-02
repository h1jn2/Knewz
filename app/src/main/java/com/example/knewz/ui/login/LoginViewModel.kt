package com.example.knewz.ui.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.domain.usecase.SignInWithEmailUseCase
import com.example.knewz.domain.usecase.SignInWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(newValue: String) { _email.value = newValue }
    fun onPasswordChange(newValue: String) { _password.value = newValue }

    fun resetState() {
        _uiState.value = AuthUiState.Idle
    }
    fun clearState() {
        _uiState.value = AuthUiState.Idle
        _email.value = ""
        _password.value = ""
    }
    fun login() {
        val emailValue = _email.value.trim()
        val passwordValue = _password.value.trim()

        if (emailValue.isEmpty() || passwordValue.isEmpty()) {
            _uiState.value = AuthUiState.Error("이메일과 비밀번호를 모두 입력해주세요.")
            return
        }

        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = signInWithEmailUseCase.execute(_email.value, _password.value)

            result.onSuccess {
                _uiState.value = AuthUiState.Success
            }.onFailure { e ->
//                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT)
                e.localizedMessage?.let { Log.d("login", it) }
                _uiState.value = AuthUiState.Error(e.localizedMessage ?: "로그인 실패")
            }
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            signInWithGoogleUseCase.execute(idToken)
                .onSuccess { _uiState.value = AuthUiState.Success }
                .onFailure { _uiState.value = AuthUiState.Error(it.message ?: "Google 로그인 실패") }
        }
    }
}

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    object Success : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}