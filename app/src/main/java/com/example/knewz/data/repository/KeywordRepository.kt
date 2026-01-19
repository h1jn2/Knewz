package com.example.knewz.data.repository

import com.example.knewz.ui.keyword.KeywordItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface KeywordRepository {
    suspend fun addKeyword(keywordName: String): Result<Unit>
    fun getKeywords(): Flow<List<KeywordItem>>
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

    override fun getKeywords(): Flow<List<KeywordItem>> = callbackFlow {
        val uid = auth.currentUser?.uid ?: return@callbackFlow
        val ref = database.child("users").child(uid).child("keywords")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.children.mapNotNull { child ->
                    child.getValue(KeywordItem::class.java)?.copy(id = child.key ?: "")
                }
                trySend(items) // UI로 데이터 전송
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        ref.addValueEventListener(listener)
        awaitClose { ref.removeEventListener(listener) }
    }

}