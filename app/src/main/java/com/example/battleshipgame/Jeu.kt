package com.example.battleshipgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class Jeu : AppCompatActivity() {

    // Variable stockant la combinaison gagnante
    private lateinit var selectedCombination: List<String>

    private val playerChoices = mutableListOf<String>()
    private var clickCount = 0

    // Liste de toutes les combinaisons gagnantes possibles
    private val winningPossibilities: List<List<String>> = listOf(
        listOf("A1", "A2", "A3"),
        listOf("A2", "A3", "A4"),
        listOf("B3", "A4", "A5"),
        listOf("B1", "B2", "B3"),
        listOf("B2", "B3", "B4"),
        listOf("B3", "B4", "B5"),
        listOf("C1", "C2", "C3"),
        listOf("C2", "C3", "C4"),
        listOf("C3", "C4", "C5"),
        listOf("C1", "C2", "C3"),
        listOf("C2", "C3", "C4"),
        listOf("C3", "C4", "C5"),
        listOf("E1", "E2", "E3"),
        listOf("E2", "E3", "E4"),
        listOf("E3", "E4", "E5"),
        listOf("A1", "B1", "C1"),
        listOf("B1", "C1", "D1"),
        listOf("C1", "D1", "E1"),
        listOf("A2", "B2", "C2"),
        listOf("B2", "C2", "D2"),
        listOf("C2", "D2", "E2"),
        listOf("A3", "B3", "C3"),
        listOf("B3", "C3", "D3"),
        listOf("C3", "D3", "E3"),
        listOf("A4", "B4", "C4"),
        listOf("B4", "C4", "D4"),
        listOf("C4", "D4", "E4"),
        listOf("A5", "B5", "C5"),
        listOf("B5", "C5", "D5"),
        listOf("C5", "D5", "E5"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jeu)

        // Sélectionne aléatoirement une combinaison gagnante qui sera celle à trouver
        selectedCombination = winningPossibilities.random()

        // Ajouter des écouteurs de clic pour chaque bouton de la grille
        val buttons = listOf(
            findViewById<Button>(R.id.boutonJeuA1),
            findViewById<Button>(R.id.boutonJeuA2),
            findViewById<Button>(R.id.boutonJeuA3),
            findViewById<Button>(R.id.boutonJeuA4),
            findViewById<Button>(R.id.boutonJeuA5),
            findViewById<Button>(R.id.boutonJeuB1),
            findViewById<Button>(R.id.boutonJeuB2),
            findViewById<Button>(R.id.boutonJeuB3),
            findViewById<Button>(R.id.boutonJeuB4),
            findViewById<Button>(R.id.boutonJeuB5),
            findViewById<Button>(R.id.boutonJeuC1),
            findViewById<Button>(R.id.boutonJeuC2),
            findViewById<Button>(R.id.boutonJeuC3),
            findViewById<Button>(R.id.boutonJeuC4),
            findViewById<Button>(R.id.boutonJeuC5),
            findViewById<Button>(R.id.boutonJeuD1),
            findViewById<Button>(R.id.boutonJeuD2),
            findViewById<Button>(R.id.boutonJeuD3),
            findViewById<Button>(R.id.boutonJeuD4),
            findViewById<Button>(R.id.boutonJeuD5),
            findViewById<Button>(R.id.boutonJeuE1),
            findViewById<Button>(R.id.boutonJeuE2),
            findViewById<Button>(R.id.boutonJeuE3),
            findViewById<Button>(R.id.boutonJeuE4),
            findViewById<Button>(R.id.boutonJeuE5)
        )

        val feedbackTextView = findViewById<TextView>(R.id.textView_feedback)

        for (button in buttons) {
            button.setOnClickListener {
                val choice = button.text.toString()

                // Vérifie si le choix n'a pas déjà été sélectionné
                if (!playerChoices.contains(choice)) {
                    playerChoices.add(choice)
                    button.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))
                    clickCount++
                }

                var message = "froid" // Default message

                // Vérifie si le choix fait partie de la combinaison gagnante ou pas
                if (selectedCombination.contains(choice)) {
                    button.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
                    message = "Touché"
                } else {
                    message = checkProximity(listOf(choice))
                    if (message == "tiède") {
                        button.setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
                    }
                }

                // Mettre à jour le TextView avec le message approprié
                feedbackTextView.text = message

                // Vérifier si toutes les cases de la combinaison gagnante ont été trouvées
                if (playerChoices.count { it in selectedCombination } == selectedCombination.size) {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("resultat", "gagné")
                    intent.putExtra("clickCount", clickCount)
                    startActivity(intent)
                }
            }
        }
    }

    // Vérifie la proximité du choix du joueur par rapport à la combinaison gagnante et retourne un message en fonction de cette proximité
    private fun checkProximity(playerChoices: List<String>): String {
        var proximityCount = 0

        for (choice in playerChoices) {
            if (selectedCombination.any { it[0] == choice[0] && Math.abs(it[1] - choice[1]) == 1 } ||
                selectedCombination.any { it[1] == choice[1] && Math.abs(it[0] - choice[0]) == 1 }) {
                proximityCount++
            }
        }

        return when (proximityCount) {
            0 -> "froid"
            1 -> "tiède"
            2 -> "chaud"
            else -> "très chaud"
        }
    }
}