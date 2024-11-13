package com.example.habittracker.view.main


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habittracker.ui.theme.fontFamily
import com.example.habittracker.data.HabitData
import com.example.habittracker.viewModel.FirestoreViewModel
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, db: FirebaseFirestore, viewModel: FirestoreViewModel) {

    val allHabits by viewModel.habitsData.collectAsState()
    val loading by viewModel.loading.collectAsState()

    val listOfHabbits = listOf("Walking", "Exercise", "Drinking", "Study")
    val sheetState = rememberModalBottomSheetState()
    val isSheetOpen = remember { mutableStateOf(false) }


    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(color = Color.Black)
                .padding(5.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Ayush",
                    color = Color.White,
                    fontFamily = fontFamily,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "Today",
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 2.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)) {
                        append("21")
                    }
                    withStyle(style = SpanStyle(fontSize = 20.sp, color = Color.White)) {
                        append(" Streaks")
                    }
                },
                fontSize = 40.sp,
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Track your daily activity",
                fontSize = 16.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Display Habits list
            Text(
                text = "Habits",
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 0.dp, start = 15.dp)
            )

            LazyColumn {
                items(allHabits) { allHabits ->
                    HabitStyle(allHabits)
                }
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .padding(horizontal = 7.dp, vertical = 5.dp),
                thickness = 1.dp
            )

            // Button to open the habit sheet
            Button(
                onClick = {
                    isSheetOpen.value = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp)
                    .padding(top = 10.dp, start = 5.dp, end = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF191919),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Add new habit")
            }
        }

    }
    AddHabitSheet(sheetState, isSheetOpen,db,viewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddHabitSheet(
    sheetState: SheetState,
    isSheetOpen: MutableState<Boolean>,
    db: FirebaseFirestore,
    viewModel: FirestoreViewModel,) {

    var habitName by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    if (isSheetOpen.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen.value = false
            },
            //dragHandle =
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Text(text = "Add a new habit", fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(Modifier.height(20.dp))

                OutlinedTextField(
                    value = habitName,
                    onValueChange = { habitName = it },
                    label = { Text(text = "Habit Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, start = 20.dp, end = 20.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.White,
                    )
                )
                Spacer(Modifier.height(20.dp))
                Button(onClick = {
                    val habitData = HabitData("",habitName = habitName)
                    viewModel.addHabits(habitData)
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            isSheetOpen.value = false
                        }
                    }
                },
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Add habit")
                }

            }
        }
    }
}

//@Composable
//@Preview
//fun Screen() {
//    HomeScreen(navController = rememberNavController())
//}
