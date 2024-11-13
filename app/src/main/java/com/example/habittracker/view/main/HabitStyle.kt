package com.example.habittracker.view.main


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habittracker.R
import com.example.habittracker.data.HabitData
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun HabitStyle(allHabits: HabitData) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF191919)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
    ) {
        Row(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 7.dp)
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color(0xFF191919)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row (
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = allHabits.habitName,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp)
            )

        }
            Row (
                modifier = Modifier
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                Icon(
                    painter = painterResource(id = R.drawable.alarm ),
                    contentDescription = "",
                )
                Text(
                    text = "10:12",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun ShowCalander(){

}

@Composable
fun CalanderStyle(){

    Card (
        modifier = Modifier
            .height(65.dp)
            .width(52.dp)

    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            Alignment.CenterHorizontally
        ) {

            Text(
                text = "Mon",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(2.dp)
            )
            Text(
                text = "7",

            )
        }
    }
}

@Composable
fun Header() {
    Row {
        Text(
            text = "Saturday 20, May 2023",
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        IconButton(onClick = { }) {
            Box(
                modifier = Modifier
                    .size(48.dp) // Adjust the size as needed
                    .background(Color.DarkGray) // Set dark gray background
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.prev_arrow), // Replace with your drawable resource
                    contentDescription = "Previous",
                    modifier = Modifier.size(24.dp),
                )
            }
        }

        IconButton(onClick = { }) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color.DarkGray)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.next_arrow), // Replace with your drawable resource
                    contentDescription = "Next",
                    modifier = Modifier.size(24.dp),

                )
            }
        }
    }
}
@Composable
@Preview
fun MainPreview(){
    Header()
   //CalanderStyle()
   // HabitStyle(allHabits = HabitData("","achaha"))
}