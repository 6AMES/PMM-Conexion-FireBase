package com.example.pmm_conexion_firebase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import com.google.firebase.database.FirebaseDatabase

class Buscar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buscar)

        // Referencias a los elementos del formulario
        val nombreEditText = findViewById<EditText>(R.id.editText) // Campo "Nombre"
        val correoEditText = findViewById<EditText>(R.id.editText2) // Campo "Correo Electrónico"
        val telefonoEditText = findViewById<EditText>(R.id.editText3) // Campo "Número de Teléfono"
        val habilidadesEditText = findViewById<EditText>(R.id.editText4) // Campo "Habilidades"
        val enviarButton = findViewById<Button>(R.id.button) // Botón "Enviar"

        // Referencia a la base de datos de Firebase
        val database = FirebaseDatabase.getInstance()
        val busquedaRef = database.getReference("Solicitudes") // Nodo principal

        // Acción al presionar el botón de enviar
        enviarButton.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val correo = correoEditText.text.toString()
            val telefono = telefonoEditText.text.toString()
            val habilidades = habilidadesEditText.text.toString()

            // Validar los campos obligatorios
            if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || habilidades.isEmpty()) {
                Toast.makeText(this, "Por favor, llena todos los campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar el formato del correo electrónico
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, "Por favor, introduce un correo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear un objeto para representar la búsqueda de empleo
            val busqueda = Busqueda(
                nombre = nombre,
                correo = correo,
                telefono = telefono,
                habilidades = habilidades
            )

            // Guardar en Firebase
            busquedaRef.push().setValue(busqueda)
                .addOnSuccessListener {
                    Toast.makeText(this, "Solicitud enviada exitosamente", Toast.LENGTH_SHORT).show()
                    // Limpiar los campos
                    nombreEditText.text.clear()
                    correoEditText.text.clear()
                    telefonoEditText.text.clear()
                    habilidadesEditText.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al enviar la solicitud: ${it.message}", Toast.LENGTH_SHORT).show()
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

// Clase de modelo para la búsqueda de empleo
data class Busqueda(
    val nombre: String = "",
    val correo: String = "",
    val telefono: String = "",
    val habilidades: String = ""
)