package com.example.knewz.ui.mypage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    var userName by mutableStateOf("")
    var userEmail by mutableStateOf("")

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        val user = firebaseAuth.currentUser
        user?.let {
            userName = it.displayName ?: "사용자"
            userEmail = it.email ?: ""
        }
    }

    fun logout(onLogoutSuccess: () -> Unit) {
        firebaseAuth.signOut()
        onLogoutSuccess()
    }
}