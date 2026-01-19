package com.example.knewz.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface KeywordRepository {
    suspend fun addKeyword(keywordName: String): Result<Unit>
}

class KeywordRepositoryImpl @Inject constructor(
    private val database: DatabaseReference,
    private val auth: FirebaseAuth
) : KeywordRepository {

    override suspend fun addKeyword(keywordName: String): Result<Unit> {
        return try {
            val uid = auth.currentUser?.uid ?: throw Exception("로그인이 필요합니다.")

            val keywordRef = database.child("users").child(uid).child("keywords").push()

            val keywordData = mapOf(
                "keyword" to keywordName,
                "notifyEnabled" to true,
                "createdAt" to System.currentTimeMillis(),
                "lastCheckedAt" to System.currentTimeMillis()
            )

            keywordRef.setValue(keywordData).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}