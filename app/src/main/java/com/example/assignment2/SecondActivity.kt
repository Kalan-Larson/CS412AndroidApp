package com.example.assignment2

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

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Assignment2Theme {
                SecondScreen(this)
            }
        }
    }
}

@Composable
fun SecondScreen(activity: SecondActivity) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mobile Software Engineering Challenges")
        Text("1. Device compatibility")
        Text("2. Different screen sizes")
        Text("3. Battery usage")
        Text("4. Network connectivity")
        Text("5. Security and privacy")

        Button(onClick = {
            activity.finish()
        }) {
            Text("Main Activity")
        }
    }
}