package com.example.pmm_conexion_firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /* BOTONES MENÚ */

        // HOME
        val homeButton = findViewById<Button>(R.id.homeB)

        homeButton.setOnClickListener {
            val home = Intent(this, MainActivity::class.java)
            startActivity(home)
        }

        // OFRECER
        val ofrecerButton = findViewById<Button>(R.id.ofrecer)

        ofrecerButton.setOnClickListener {
            val ofrecer = Intent(this, Ofrecer::class.java)
            startActivity(ofrecer)
        }

        // BUSCAR
        val buscarButton = findViewById<Button>(R.id.buscar)

        buscarButton.setOnClickListener {
            val buscar = Intent(this, Buscar::class.java)
            startActivity(buscar)
        }
    }
}