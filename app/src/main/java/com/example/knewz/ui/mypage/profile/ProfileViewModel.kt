package com.example.knewz.ui.mypage.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val auth = com.google.firebase.auth.FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser

    private val _userEmail = MutableStateFlow(currentUser?.email ?: "")
    val userEmail = _userEmail.asStateFlow()

    private val _userName = MutableStateFlow(currentUser?.displayName ?: "사용자")
    val userName = _userName.asStateFlow()

    val isGoogleUser: Boolean = currentUser?.providerData?.any {
        it.providerId == com.google.firebase.auth.GoogleAuthProvider.PROVIDER_ID
    } ?: false

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun updateName(newName: String) {
        viewModelScope.launch {
            _uiState.value = ProfileUiState.Loading
            val profileUpdates = com.google.firebase.auth.userProfileChangeRequest {
                displayName = newName
            }

            currentUser?.updateProfile(profileUpdates)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _userName.value = newName
                        _uiState.value = ProfileUiState.Success("이름이 변경되었습니다.")
                    } else {
                        _uiState.value = ProfileUiState.Error("이름 변경 실패")
                    }
                }
        }
    }

    fun sendPasswordReset() {
        val email = currentUser?.email ?: return
        _uiState.value = ProfileUiState.Loading
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _uiState.value = ProfileUiState.Success("비밀번호 재설정 이메일을 보냈습니다.")
            } else {
                _uiState.value = ProfileUiState.Error("이메일 전송 실패")
            }
        }
    }

    fun logout(onComplete: () -> Unit) {
        auth.signOut()
        onComplete()
    }

    fun deleteAccount(onComplete: () -> Unit) {
        _uiState.value = ProfileUiState.Loading
        currentUser?.delete()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onComplete()
            } else {
                _uiState.value = ProfileUiState.Error("보안을 위해 다시 로그인 후 탈퇴해주세요.")
            }
        }
    }

    fun resetState() { _uiState.value = ProfileUiState.Idle }
}

