package com.example.calculoimc

import android.content.Context
import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val nombre: String,
    val apellido: String,
    val peso: Double,
    val estatura: Double,
    val fotoPerfil: String
): Parcelable

class GuardarUsuario(val context: Context) {
    val sharedPreferences = context.getSharedPreferences("usuarios", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()

    fun guardarUsuario(data: Data) {
        editor.putString(data.nombre, gson.toJson(data))
        editor.apply()

    }

    fun obtenerUsuario(nombre: String): Data? {
        val json = sharedPreferences.getString(nombre, null)
        return gson.fromJson(json, Data::class.java)
    }
}