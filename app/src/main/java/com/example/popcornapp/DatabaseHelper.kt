package com.example.popcornapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DatabaseHelper(context: Context) : SQLiteOpenHelper(
   context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION,
    ) {
    companion object {
        const val DATABASE_NAME = "popcornapp.db"
        const val DATABASE_VERSION = 2
        private const val TABLE_USUARIOS = "usuarios"
        private const val COL_ID = "id"
        private const val COL_NOMBRE = "nombre"
        private const val COL_CORREO = "correo"
        private const val COL_PASSWORD = "password"

    }
    //Metodo parra crear la base de datos
    override fun onCreate(db: SQLiteDatabase?) {
        val crearTablaUsuarios ="""
            CREATE TABLE $TABLE_USUARIOS (
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_NOMBRE TEXT NOT NULL,
            $COL_CORREO TEXT NOT NULL UNIQUE,
            $COL_PASSWORD TEXT NOT NULL)
            
        """.trimIndent()

        db?.execSQL(crearTablaUsuarios)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    fun registrarUsuario(nombre: String, correo: String, password: String):Boolean {

       val db = writableDatabase
        val datos = ContentValues().apply {
            put(COL_NOMBRE, nombre)
            put(COL_CORREO, correo)
            put(COL_PASSWORD, password)

        }

        val resultado = db.insert(TABLE_USUARIOS, null, datos)
        db.close()
       return resultado != -1L

    }
}

