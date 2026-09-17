package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Assignment2Theme {
                MainScreen(this)
            }
        }
    }
}

@Composable
fun MainScreen(activity: MainActivity) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Kalan Larson")
        Text(text = "Student ID: 1448164")

        Button(onClick = {
            val intent = Intent(activity, SecondActivity::class.java)
            activity.startActivity(intent)
        }) {
            Text("Start Activity Explicitly")
        }

        Button(onClick = {
            val intent = Intent("com.example.assignment2.SECOND_ACTIVITY")
            activity.startActivity(intent)
        }) {
            Text("Start Activity Implicitly")
        }
    }
}