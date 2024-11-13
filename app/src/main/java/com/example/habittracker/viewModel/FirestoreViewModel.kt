package com.example.habittracker.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.repository.FirestoreRepository.FirestoreRepository
import com.example.habittracker.data.HabitData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirestoreViewModel @Inject constructor(
    private val repository: FirestoreRepository
) :ViewModel(){

    private val _habitsData = MutableStateFlow<List<HabitData>>(emptyList())
    val habitsData: StateFlow<List<HabitData>> get() = _habitsData

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    fun addHabits(habitData: HabitData){
        viewModelScope.launch {
            repository.addDataToFirestore(habitData)
        }
    }

    fun getHabitData(habitData: HabitData){
        viewModelScope.launch {
            _loading.value = true
            val habitsDataVM = repository.getDataFromFirestore()
            _habitsData.value  = habitsDataVM
            _loading.value = false
        }
    }
}