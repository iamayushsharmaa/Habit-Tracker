package com.example.habittracker.view.navigation


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habittracker.data.BottomNavItem
import com.example.habittracker.repository.authRepository.GoogleAuthUiClient
import com.example.habittracker.viewModel.SignInViewModel
import com.example.habittracker.view.main.AnalyticScreen
import com.example.habittracker.view.main.BottomNavBar
import com.example.habittracker.view.main.HomeScreen
import com.example.habittracker.view.main.ProfileScreen
import com.example.habittracker.viewModel.FirestoreViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineExceptionHandler


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    googleAuthUiClient: GoogleAuthUiClient,
    SignInViewModel: SignInViewModel,
    db: FirebaseFirestore
) {
    val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", "There has been an issue: ", throwable)
    }
    val viewModel =  viewModel<FirestoreViewModel>()

    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalFontFamilyResolver provides createFontFamilyResolver(LocalContext.current, handler)
    ) {
        Scaffold(
            bottomBar = {
                BottomNavBar(navController = navController,modifier = Modifier.padding(bottom = 26.dp)) // Remove manual padding here
            }
        ) { innerPadding ->

            Box(modifier = Modifier.padding(innerPadding)) {
                NavigationGraph(
                    navController,
                    googleAuthUiClient,
                    SignInViewModel,
                    db,
                    viewModel
                )
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
    SignInViewModel: SignInViewModel,
    db: FirebaseFirestore,
    viewModel: FirestoreViewModel
) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController,db, viewModel)
        }
        composable(BottomNavItem.Analytic.route) {
            AnalyticScreen()
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen(
                navController,
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    LaunchedEffect(Unit) {
                        googleAuthUiClient.signOut()
                        navController.navigate("login_screen") {
                            popUpTo(0)
                        }
                    }
                },
                SignInViewModel = SignInViewModel
            )
        }
    }
}
