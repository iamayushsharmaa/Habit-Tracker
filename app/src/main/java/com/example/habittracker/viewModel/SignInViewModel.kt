package com.example.habittracker.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.habittracker.data.SignInResult
import com.example.habittracker.data.SigninState
import com.example.habittracker.repository.authRepository.createUserWithEmailPassword
import com.example.habittracker.repository.authRepository.signInWithEmailPassword
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {

    private val _state = MutableStateFlow(SigninState())
    val state = _state.asStateFlow()

    fun onSignInResult(result : SignInResult){
        _state.update {it.copy(
            isSignInSuccesful = result.data != null,
            signInError = result.errorMessage
        ) }
    }
    fun resetState(){
        _state.update { SigninState() }
    }

    fun signInWithEmail(email: String, password: String, auth: FirebaseAuth, navController: NavController) {
        viewModelScope.launch {
            signInWithEmailPassword(
                email = email,
                password = password,
                auth = auth,
                navController = navController,
                onSuccess = {

                    _state.update { it.copy(isSignInSuccesful = true) }
                    navController.navigate("main_screen")
                },
                onFailure = { errorMessage ->
                    // Handle failure: Show error message
                    _state.update { it.copy(signInError = errorMessage) }
                }
            )
        }
    }

    fun createUserWithEmailPassword(email: String, password: String, auth: FirebaseAuth, navController: NavController) {
        viewModelScope.launch {
            createUserWithEmailPassword(
                email = email,
                password = password,
                auth = auth,
                navController = navController,
                onSuccess = {
                    _state.update { it.copy(isSignInSuccesful = true) }
                    navController.navigate("main_screen")
                },
                onFailure = { errorMessage ->
                    _state.update { it.copy(signInError = errorMessage) }
                }
            )
        }
    }


}