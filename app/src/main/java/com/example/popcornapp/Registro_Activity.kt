package com.example.popcornapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Registro_Activity : AppCompatActivity() {


    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnRegistro: Button



    private lateinit var dbHelper: DatabaseHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registro)


        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPassword)
        btnRegistro = findViewById(R.id.btnRegistro)

        dbHelper = DatabaseHelper(this)

        btnRegistro.setOnClickListener {
            registrarUsuario()
        }

    }

    private fun registrarUsuario() {
        val nombre = etNombre.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()
        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = dbHelper.registrarUsuario(nombre, correo, password)
        if(resultado){
            Toast.makeText(this, "Usuario Registrado correctamente", Toast.LENGTH_SHORT).show()
            finish()

        }else{
            Toast.makeText(this, "Usuario no registrado correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}