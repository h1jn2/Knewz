package com.example.knewz.ui.components

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.knewz.R
import com.example.knewz.ui.login.AuthUiState
import com.example.knewz.ui.login.LoginViewModel
import com.example.knewz.ui.theme.StrokeGray
import com.example.knewz.ui.theme.TextMediumGray
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

@Composable
fun LoginForm(
    viewModel: LoginViewModel,
    onNavigateToSignUp: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            account.idToken?.let { token ->
                viewModel.loginWithGoogle(token)
            }
        } catch (e: ApiException) {
            Log.e("GoogleAuth", "Google sign in failed", e)
        }
    }
    val googleSignInClient = remember {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id)) // firebase에서 생성된 Web Client ID
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(uiState) {
        if (uiState is AuthUiState.Success) {
            onLoginSuccess()
        }
    }

    Text(
        text = "로그인",
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(Modifier.height(4.dp))
    Text(
        text = "계정에 로그인하여 맞춤 뉴스를 받아보세요",
        style = MaterialTheme.typography.bodyMedium,
        color = TextMediumGray
    )

    Spacer(Modifier.height(16.dp))

    InputField(
        label = "이메일",
        placeholder = "example@email.com",
        value = email,
        onValueChange = { email = it },
        leadingIcon = {
            Icon(Icons.Outlined.Email, contentDescription = null)
        }
    )
    Spacer(Modifier.height(16.dp))
    InputField(
        label = "비밀번호",
        placeholder = "••••••••",
        value = password,
        onValueChange = { password = it },
        isPassword = true,
        leadingIcon = {
            Icon(Icons.Outlined.Lock, contentDescription = null)
        }
    )

    Spacer(Modifier.height(16.dp))

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        onClick = { viewModel.login() },
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        enabled = uiState !is AuthUiState.Loading
    ) {
        if (uiState is AuthUiState.Loading) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
        } else {
            Text("로그인")
        }
    }

    Spacer(Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = StrokeGray
        )

        Text(
            text = "또는",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = 1.dp,
            color = StrokeGray
        )
    }

    Spacer(Modifier.height(16.dp))
    SocialLoginButton(
        imageRes = R.drawable.ic_google_logo,
        text = "Google 계정으로 로그인",
        textColor = Color.Black,
        backgroundColor = Color.White,
        onClick = {
            googleSignInLauncher.launch(googleSignInClient.signInIntent)
        }
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "계정이 없으신가요?",
            style = MaterialTheme.typography.bodySmall,
            color = TextMediumGray
        )
        Spacer(Modifier.width(4.dp))
        TextButton(onClick = { onNavigateToSignUp() }) {
            Text("회원가입", fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}