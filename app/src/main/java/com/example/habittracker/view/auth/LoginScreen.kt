package com.example.habittracker.view.auth


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habittracker.R
import com.example.habittracker.data.SigninState
import com.example.habittracker.viewModel.SignInViewModel
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    mAuth: FirebaseAuth,
    signInViewModel: SignInViewModel,
    state: SigninState,
    applicationContext: Context,
    navController: NavController,
    onGoogleSignClick: () -> Unit,
) {

    var email by remember{ mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(it)
                .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start

        ) {
            Text(
                text = "Sign in",
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )
            Text(
                text = "Please fill all the details to sign in.",
                modifier = Modifier.padding(horizontal = 20.dp),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email",color = Color.Black) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 20.dp, end = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.DarkGray,
                    unfocusedBorderColor = Color.DarkGray,
                    cursorColor = Color.DarkGray,
                )
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password", color = Color.Black)},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 20.dp, end = 20.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.DarkGray,
                    unfocusedBorderColor = Color.DarkGray,
                    cursorColor = Color.DarkGray,

                    )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                Arrangement.End
            ) {
                Text(text = "Forget password?", Modifier.clickable { })

            }
            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .padding(top = 10.dp, start = 5.dp, end = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Log in")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "or with",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 150.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))


            SignInGoogle(state = state,onGoogleSignClick)

            SignInFacbook {

            }

            val annotatedText = buildAnnotatedString {
                append("Don't have an account? ")

                pushStringAnnotation(tag = "SIGN_UP", annotation = "sign_up")
                withStyle(style = SpanStyle(color = Color(0xFF0538D1))) {
                    append("Sign up!")
                }
                pop()
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                ClickableText(
                    text = annotatedText,
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(tag = "SIGN_UP", start = offset, end = offset)
                            .firstOrNull()?.let {
                                navController.navigate("signUp")
                            }
                    }
                )
            }


        }

    }

}

@Composable
fun SignInGoogle(
    state: SigninState,
    onGoogleSignClick: () -> Unit
){

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let{ error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()

        }
    }
    Button(onClick = onGoogleSignClick,
        modifier = Modifier
            .height(58.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, start = 5.dp, end = 5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.googlesvg),
            contentDescription = "Google Sign-In",
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = "Sign in with Google")
    }
}

@Composable
fun SignInFacbook(onClick:() -> Unit){

    Button(onClick = onClick,
        modifier = Modifier
            .height(58.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, start = 5.dp, end = 5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icons_fb),
            contentDescription = "Facebook Sign-In",
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = "Sign in with Facebook")
    }
}

//@Preview
//@Composable
//fun LoginPre(){
//    LoginScreen(viewModel = viewModel(), state = SigninState(), onGoogleSignClick,applicationContext = LocalContext.current, navController = rememberNavController())
//}