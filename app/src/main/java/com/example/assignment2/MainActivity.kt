package com.example.assignment2

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {

    private val receiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }

        val filter = IntentFilter("com.example.MY_ACTION")
        registerReceiver(receiver, filter, RECEIVER_NOT_EXPORTED)

        setContent {
            Assignment2Theme {
                MainScreen(this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}

@Composable
fun MainScreen(activity: MainActivity) {

    val grade = remember { mutableStateOf("") }

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

        Button(onClick = {
            val intent = Intent(activity, MyService::class.java)
            ContextCompat.startForegroundService(activity, intent)
        }) {
            Text("Start Service")
        }

        Button(onClick = {
            val intent = Intent(activity, MyService::class.java)

            val connection = object : ServiceConnection {
                override fun onServiceConnected(
                    name: ComponentName?,
                    binder: IBinder?
                ) {
                    val service = (binder as MyService.MyBinder).getService()
                    grade.value = service.getMyGrade()
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                }
            }

            activity.bindService(
                intent,
                connection,
                Context.BIND_AUTO_CREATE
            )
        }) {
            Text("Bind Service")
        }

        Text("Grade: ${grade.value}")

        Button(onClick = {
            val intent = Intent("com.example.MY_ACTION")
            intent.setPackage(activity.packageName)
            activity.sendBroadcast(intent)
        }) {
            Text("Send Broadcast")
        }
    }
}