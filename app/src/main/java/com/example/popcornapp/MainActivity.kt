package com.example.popcornapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etCorreo: EditText

    private lateinit var etPass: EditText
    private lateinit var btnInicio: Button
    private lateinit var dbHelper: DatabaseHelper





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val tvCrearCuenta = findViewById<TextView>(R.id.tvCrearCuenta)

        etCorreo = findViewById(R.id.etCorreo)
        etPass = findViewById(R.id.etPassword)
        btnInicio = findViewById(R.id.btnIngresar)
        dbHelper = DatabaseHelper(this)

        btnInicio.setOnClickListener {
            iniciarSesion();
        }


        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, Registro_Activity::class.java)
            startActivity(intent)
        }


    }


    private fun iniciarSesion() {
        val  correo = etCorreo.text.toString().trim()
        val pass = etPass.text.toString().trim()

        if (correo.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = dbHelper.iniciarSesion(correo, pass)
        if (resultado){
            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            finish()
        }else{
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}