package com.example.battleshipgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultat = intent.getStringExtra("resultat") ?: "inconnu"
        val clickCount = intent.getIntExtra("clickCount", -1)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val clickCountTextView = findViewById<TextView>(R.id.clickCountTextView)
        val homeButton = findViewById<Button>(R.id.homeButton)

        resultTextView.text = when (resultat) {
            "gagné" -> "Gagné"
            "perdu" -> "Perdu"
            else -> "Résultat inconnu"
        }

        clickCountTextView.text = if (clickCount >= 0) {
            "Nombre de clics : $clickCount"
        } else {
            "Nombre de clics : Non disponible"
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }
}