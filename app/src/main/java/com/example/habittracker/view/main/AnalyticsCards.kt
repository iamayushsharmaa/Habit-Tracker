package com.example.habittracker.view.main

import androidx.annotation.PluralsRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AnalyticsCards() {

    Card(
        modifier = Modifier
            .padding(15.dp)
            .height(220.dp)
            .fillMaxWidth()

    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ){

            Text(
                text = "Streaks : 4",
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                )
            Text(
                text = "Total days : 8",
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                )
            Text(
                text = "Today habits : 18",
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                )
            Text(
                text = "Starting day : 1/12/24",
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
            )
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    AnalyticsCards()
}