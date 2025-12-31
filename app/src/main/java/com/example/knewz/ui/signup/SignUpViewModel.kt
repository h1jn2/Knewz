package com.example.knewz.ui.signup

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knewz.domain.usecase.SignUpWithEmailUseCase
import com.example.knewz.ui.login.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpWithEmailUseCase: SignUpWithEmailUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState = _uiState.asStateFlow()

    // 입력 데이터 상태
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    // 에러 메시지 상태
    var nameError by mutableStateOf<String?>(null)
    var emailError by mutableStateOf<String?>(null)
    var passwordError by mutableStateOf<String?>(null)
    var confirmPasswordError by mutableStateOf<String?>(null)

    // 유효성 검사 로직
    fun validate(): Boolean {
        var isValid = true

        if (name.length < 2) {
            nameError = "이름은 2자 이상 입력해주세요."
            isValid = false
        } else {
            nameError = null
        }

        // 이메일 검사 (정규식)
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!email.matches(emailPattern.toRegex())) {
            emailError = "올바른 이메일 형식이 아닙니다."
            isValid = false
        } else {
            emailError = null
        }

        // 비밀번호 검사 (8자 이상)
        if (password.length < 8) {
            passwordError = "비밀번호는 8자 이상이어야 합니다."
            isValid = false
        } else {
            passwordError = null
        }

        // 비밀번호 확인 검사
        if (password != confirmPassword) {
            confirmPasswordError = "비밀번호가 일치하지 않습니다."
            isValid = false
        } else {
            confirmPasswordError = null
        }

        return isValid
    }

    fun signUp(onSuccess: () -> Unit) {
        if (validate()) {
            viewModelScope.launch {
                _uiState.value = AuthUiState.Loading

                val result = signUpWithEmailUseCase.execute(name, email, password)

                result.onSuccess {
                    _uiState.value = AuthUiState.Success
                    onSuccess()
                }.onFailure { e ->
                    Log.e("SignUp", "Firebase 계정 생성 실패: ${e.message}")
                    val errorMessage = e.message ?: "회원가입 실패"

                    if (errorMessage.contains("이메일") || errorMessage.contains("email")) {
                        emailError = "이미 사용 중인 이메일입니다."
                        _uiState.value = AuthUiState.Idle
                    } else {
                        _uiState.value = AuthUiState.Error(errorMessage)
                    }
                }
            }
        } else {
            Log.d("SignUp", "유효성 검사 실패: nameError=$nameError, emailError=$emailError")
        }
    }
}