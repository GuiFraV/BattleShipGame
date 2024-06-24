package com.example.battleshipgame

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class JeuDifficileActivity : AppCompatActivity() {

    private lateinit var selectedCombination: List<String>
    private val playerChoices = mutableListOf<String>()
    private var clickCount = 0
    private lateinit var feedbackTextView: TextView

    private val winningPossibilities: List<List<String>> = listOf(
        // Combinaisons horizontales
        listOf("A1", "A2", "A3", "A4"), listOf("A2", "A3", "A4", "A5"), listOf("A3", "A4", "A5", "A6"), listOf("A4", "A5", "A6", "A7"),
        listOf("B1", "B2", "B3", "B4"), listOf("B2", "B3", "B4", "B5"), listOf("B3", "B4", "B5", "B6"), listOf("B4", "B5", "B6", "B7"),
        listOf("C1", "C2", "C3", "C4"), listOf("C2", "C3", "C4", "C5"), listOf("C3", "C4", "C5", "C6"), listOf("C4", "C5", "C6", "C7"),
        listOf("D1", "D2", "D3", "D4"), listOf("D2", "D3", "D4", "D5"), listOf("D3", "D4", "D5", "D6"), listOf("D4", "D5", "D6", "D7"),
        listOf("E1", "E2", "E3", "E4"), listOf("E2", "E3", "E4", "E5"), listOf("E3", "E4", "E5", "E6"), listOf("E4", "E5", "E6", "E7"),
        listOf("F1", "F2", "F3", "F4"), listOf("F2", "F3", "F4", "F5"), listOf("F3", "F4", "F5", "F6"), listOf("F4", "F5", "F6", "F7"),
        listOf("G1", "G2", "G3", "G4"), listOf("G2", "G3", "G4", "G5"), listOf("G3", "G4", "G5", "G6"), listOf("G4", "G5", "G6", "G7"),
        // Combinaisons verticales
        listOf("A1", "B1", "C1", "D1"), listOf("B1", "C1", "D1", "E1"), listOf("C1", "D1", "E1", "F1"), listOf("D1", "E1", "F1", "G1"),
        listOf("A2", "B2", "C2", "D2"), listOf("B2", "C2", "D2", "E2"), listOf("C2", "D2", "E2", "F2"), listOf("D2", "E2", "F2", "G2"),
        listOf("A3", "B3", "C3", "D3"), listOf("B3", "C3", "D3", "E3"), listOf("C3", "D3", "E3", "F3"), listOf("D3", "E3", "F3", "G3"),
        listOf("A4", "B4", "C4", "D4"), listOf("B4", "C4", "D4", "E4"), listOf("C4", "D4", "E4", "F4"), listOf("D4", "E4", "F4", "G4"),
        listOf("A5", "B5", "C5", "D5"), listOf("B5", "C5", "D5", "E5"), listOf("C5", "D5", "E5", "F5"), listOf("D5", "E5", "F5", "G5"),
        listOf("A6", "B6", "C6", "D6"), listOf("B6", "C6", "D6", "E6"), listOf("C6", "D6", "E6", "F6"), listOf("D6", "E6", "F6", "G6"),
        listOf("A7", "B7", "C7", "D7"), listOf("B7", "C7", "D7", "E7"), listOf("C7", "D7", "E7", "F7"), listOf("D7", "E7", "F7", "G7")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jeu_difficile)

        selectedCombination = winningPossibilities.random()
        Log.d("JeuDifficileActivity", "Combinaison gagnante : $selectedCombination")

        // Initialiser le TextView pour le feedback
        feedbackTextView = TextView(this).apply {
            id = TextView.generateViewId()
            layoutParams = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams(
                androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.WRAP_CONTENT,
                androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            text = "Cliquez sur une case pour commencer"
            textSize = 18f
        }

        // Ajouter le TextView au layout
        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main).addView(feedbackTextView)

        // Positionner le TextView en dessous de la grille
        feedbackTextView.layoutParams = (feedbackTextView.layoutParams as androidx.constraintlayout.widget.ConstraintLayout.LayoutParams).apply {
            topToBottom = R.id.Tableau2
            startToStart = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
            topMargin = 16
        }

        val buttons = listOf(
            R.id.boutonJeu2A1, R.id.boutonJeu2A2, R.id.boutonJeu2A3, R.id.boutonJeu2A4, R.id.boutonJeu2A5, R.id.boutonJeu2A6, R.id.boutonJeu2A7,
            R.id.boutonJeu2B1, R.id.boutonJeu2B2, R.id.boutonJeu2B3, R.id.boutonJeu2B4, R.id.boutonJeu2B5, R.id.boutonJeu2B6, R.id.boutonJeu2B7,
            R.id.boutonJeu2C1, R.id.boutonJeu2C2, R.id.boutonJeu2C3, R.id.boutonJeu2C4, R.id.boutonJeu2C5, R.id.boutonJeu2C6, R.id.boutonJeu2C7,
            R.id.boutonJeu2D1, R.id.boutonJeu2D2, R.id.boutonJeu2D3, R.id.boutonJeu2D4, R.id.boutonJeu2D5, R.id.boutonJeu2D6, R.id.boutonJeu2D7,
            R.id.boutonJeu2E1, R.id.boutonJeu2E2, R.id.boutonJeu2E3, R.id.boutonJeu2E4, R.id.boutonJeu2E5, R.id.boutonJeu2E6, R.id.boutonJeu2E7,
            R.id.boutonJeu2F1, R.id.boutonJeu2F2, R.id.boutonJeu2F3, R.id.boutonJeu2F4, R.id.boutonJeu2F5, R.id.boutonJeu2F6, R.id.boutonJeu2F7,
            R.id.boutonJeu2G1, R.id.boutonJeu2G2, R.id.boutonJeu2G3, R.id.boutonJeu2G4, R.id.boutonJeu2G5, R.id.boutonJeu2G6, R.id.boutonJeu2G7
        ).map { findViewById<Button>(it) }

        for (button in buttons) {
            button.setOnClickListener {
                handleButtonClick(button.text.toString(), button)
            }
        }
    }

    private fun handleButtonClick(choice: String, button: Button) {
        clickCount++
        Log.d("JeuDifficileActivity", "Clic sur $choice, Nombre de clics : $clickCount")

        if (!playerChoices.contains(choice)) {
            playerChoices.add(choice)
            button.setBackgroundColor(resources.getColor(android.R.color.holo_blue_light))
        }

        val message = if (selectedCombination.contains(choice)) {
            button.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            "Touché"
        } else {
            val proximityMessage = checkProximity(listOf(choice))
            if (proximityMessage == "tiède") {
                button.setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
            }
            proximityMessage
        }

        feedbackTextView.text = message
        Log.d("JeuDifficileActivity", "Message affiché : $message")

        checkGameEnd()
    }

    private fun checkProximity(playerChoices: List<String>): String {
        var proximityCount = 0

        for (choice in playerChoices) {
            if (selectedCombination.any { it[0] == choice[0] && Math.abs(it[1].toString().toInt() - choice[1].toString().toInt()) == 1 } ||
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

    private fun checkGameEnd() {
        if (playerChoices.count { it in selectedCombination } == selectedCombination.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("resultat", "gagné")
            intent.putExtra("clickCount", clickCount)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Quitter le jeu")
            .setMessage("Êtes-vous sûr de vouloir quitter le jeu ?")
            .setPositiveButton("Oui") { _, _ ->
                super.onBackPressed()
            }
            .setNegativeButton("Non", null)
            .show()
    }
}