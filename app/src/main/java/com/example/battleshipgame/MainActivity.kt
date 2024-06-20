package com.example.battleshipgame

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.battleshipgame.ui.theme.BattleShipGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleShipGameTheme {
                MainScreen(
                    onEasyClick = { navigateToJeuActivity() },
                    onHardClick = { /* Add navigation to another activity here */ }
                )
            }
        }
    }

    private fun navigateToJeuActivity() {
        val intent = Intent(this, Jeu::class.java)
        startActivity(intent)
    }
}

@Composable
fun MainScreen(onEasyClick: () -> Unit, onHardClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Battleship", modifier = Modifier.padding(bottom = 16.dp))
        Button(onClick = onEasyClick) {
            Text(text = "Facile")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onHardClick) {
            Text(text = "Difficile")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BattleShipGameTheme {
        MainScreen(
            onEasyClick = {},
            onHardClick = {}
        )
    }
}