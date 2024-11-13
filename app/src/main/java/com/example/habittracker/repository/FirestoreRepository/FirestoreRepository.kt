package com.example.habittracker.repository.FirestoreRepository

import com.example.habittracker.data.HabitData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirestoreRepositoryImpl(): FirestoreRepository {

    private val firestoreDb = Firebase.firestore

    override suspend fun addDataToFirestore(habitData: HabitData): Boolean {
        return try {
            firestoreDb.collection("Habits")
                .add(habitData)
                .await()
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getDataFromFirestore(): List<HabitData> {
        return try {
            val snapshot = firestoreDb.collection("Habits").get().await()
            snapshot.toObjects(HabitData::class.java)
        } catch (e: Exception) {
            emptyList()
        }
    }


}