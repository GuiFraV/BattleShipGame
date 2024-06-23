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
import androidx.compose.ui.unit.dp
import com.example.battleshipgame.ui.theme.BattleShipGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BattleShipGameTheme {
                // Sur l'écran d'accueil, on affiche 2 choix : facile ou difficile, en fonction du choix, on appelle la fonction qui correspond
                MainScreen(
                    onEasyClick = { navigateToJeuActivity() },
                    onHardClick = { navigateToJeuDifficileActivity() }
                )
            }
        }
    }

    // Redirige vers le jeu niveau facile
    private fun navigateToJeuActivity() {
        val intent = Intent(this, Jeu::class.java)
        startActivity(intent)
    }

    // Redirige vers le jeu niveau difficile
    private fun navigateToJeuDifficileActivity() {
        val intent = Intent(this, JeuDifficileActivity::class.java)
        startActivity(intent)
    }
}

// Ecran principale
@Composable
fun MainScreen(onEasyClick: () -> Unit, onHardClick: () -> Unit) {
    // centre les éléments sur toute la largeur de l'écran
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Battleship", modifier = Modifier.padding(bottom = 16.dp))
        // Bouton pour le mode facile
        Button(onClick = onEasyClick) {
            Text(text = "Facile")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Bouton pour le mode difficile
        Button(onClick = onHardClick) {
            Text(text = "Difficile")
        }
    }
}