package com.example.habittracker.repository.authRepository

import android.text.TextUtils
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


suspend fun signInWithEmailPassword(
    email: String,
    password: String,
    auth: FirebaseAuth,
    navController: NavController,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit
) {
    if (TextUtils.isEmpty(email)) {
        onFailure("Please enter email.")  // Call failure callback
        return
    }

    if (TextUtils.isEmpty(password)) {
        onFailure("Please enter password.")  // Call failure callback
        return
    }

    try {
        // Try signing in the user
        auth.signInWithEmailAndPassword(email, password).await()

        // Call success callback if the sign-in is successful
        onSuccess()
    } catch (e: Exception) {
        // Call failure callback if something goes wrong
        onFailure(e.message ?: "Login failed! Please try again later.")
    }
}



suspend fun createUserWithEmailPassword(
    email: String,
    password: String,
    auth: FirebaseAuth,
    navController: NavController,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit
) {
    if (email.isEmpty()) {
        onFailure("Please enter email.")
        return
    }

    if (password.isEmpty()) {
        onFailure("Please enter password.")
        return
    }

    if (password.length < 6) {
        onFailure("Password should be at least 6 characters long.")
        return
    }

    try {
        auth.createUserWithEmailAndPassword(email, password).await()
        onSuccess()
    } catch (e: Exception) {
        onFailure(e.message ?: "Registration failed! Please try again later.")
    }
}
