package com.example.ticketbooking

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ticketbooking.ui.theme.TicketBookingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicketBookingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TicketBookingScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TicketBookingScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var seats by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, "Booked Successfully")
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Movie: Harry Potter")
        Text(text = "Price: 300")

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.padding(top = 16.dp)
        )

        TextField(
            value = seats,
            onValueChange = { seats = it },
            label = { Text("Seats") },
            modifier = Modifier.padding(top = 8.dp)
        )

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.padding(top = 8.dp)
        )

        Button(
            onClick = {
                val price = (seats.toIntOrNull() ?: 0) * 300
                if (name.isNotBlank() && (seats.toIntOrNull() ?: 0) > 0 && amount.toIntOrNull() == price) {
                    Toast.makeText(context, "Booked Successfully", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent.createChooser(intent, "Send booking details via"))
                } else {
                    Toast.makeText(context, "Booking Failed", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Book")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicketBookingScreenPreview() {
    TicketBookingTheme {
        TicketBookingScreen()
    }
}
