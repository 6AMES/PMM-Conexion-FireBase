package com.example.pmm_conexion_firebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class Ofrecer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ofrecer)

        // Referencias a los elementos del formulario
        val empresaEditText = findViewById<EditText>(R.id.editText5)
        val correoEditText = findViewById<EditText>(R.id.editText6)
        val descripcionEditText = findViewById<EditText>(R.id.editText7)
        val requisitosEditText = findViewById<EditText>(R.id.editText8)
        val salarioEditText = findViewById<EditText>(R.id.editText9)
        val publicarButton = findViewById<Button>(R.id.button2)

        // Referencia a la base de datos de Firebase
        val database = FirebaseDatabase.getInstance()
        val empleoRef = database.getReference("Ofertas") // Nodo principal

        // Acción al presionar el botón de publicar
        publicarButton.setOnClickListener {
            val empresa = empresaEditText.text.toString()
            val correo = correoEditText.text.toString()
            val descripcion = descripcionEditText.text.toString()
            val requisitos = requisitosEditText.text.toString()
            val salario = salarioEditText.text.toString()

            // Validar los campos obligatorios
            if (empresa.isEmpty() || correo.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear un objeto para representar la vacante
            val vacante = Vacante(
                empresa = empresa,
                correo = correo,
                descripcion = descripcion,
                requisitos = requisitos,
                salario = if (salario.isEmpty()) "No especificado" else salario
            )

            // Guardar en Firebase
            empleoRef.push().setValue(vacante)
                .addOnSuccessListener {
                    Toast.makeText(this, "Vacante publicada exitosamente", Toast.LENGTH_SHORT).show()
                    // Limpiar los campos
                    empresaEditText.text.clear()
                    correoEditText.text.clear()
                    descripcionEditText.text.clear()
                    requisitosEditText.text.clear()
                    salarioEditText.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al publicar la vacante: ${it.message}", Toast.LENGTH_SHORT).show()
                }
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

// Clase de modelo para la vacante
data class Vacante(
    val empresa: String = "",
    val correo: String = "",
    val descripcion: String = "",
    val requisitos: String = "",
    val salario: String = ""
)