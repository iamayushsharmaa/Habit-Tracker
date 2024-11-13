package com.example.habittracker.repository.FirestoreRepository

import com.example.habittracker.data.HabitData

interface FirestoreRepository {

    suspend fun addDataToFirestore(habitData: HabitData): Boolean
    suspend fun getDataFromFirestore(): List<HabitData>

}